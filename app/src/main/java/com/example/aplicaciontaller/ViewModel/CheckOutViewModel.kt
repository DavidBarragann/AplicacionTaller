package com.example.aplicaciontaller.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciontaller.Data.model.CartProduct
import com.example.aplicaciontaller.Data.repository.CartRepository
import com.example.aplicaciontaller.Data.repository.ProductRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Locale

data class CheckoutUiState(
    val cartProducts: List<CartProduct> = emptyList(),
    val subtotal: Double = 0.0,
    val shippingFee: Double = 35.0,
    val total : Double = 0.0,
    val userLocation: LatLng? = null,
    val locationAddres: String = "Obteniendo ubicación...",
    val selectedPaymentMehtod: String = "Tarjeta de Crédito / Débito",
    val hasLocationPermission: Boolean = false,
    val isOrderPlaced: Boolean = false
)
class CheckOutViewModel (private val cartRepository: CartRepository,
    private val productRepository: ProductRepository): ViewModel(){

    private val _cartProducts: Flow<List<CartProduct>> = cartRepository.cartItems
        .map { items ->
            items.mapNotNull { item ->
                productRepository.getProductById(item.productId)?.let {
                    product ->
                    CartProduct(productId = product, quantity = item.quantity)
                }
            }
        }

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    private val _selectedPaymentMethod = MutableStateFlow("Tarjeta de crédito débito")
    private val _hasLocationPermission = MutableStateFlow(false)
    private val _isOrderPlaced = MutableStateFlow(false)

    val uiState: StateFlow<CheckoutUiState> = combine(
        _cartProducts,
        _userLocation,
        _selectedPaymentMethod,
        _hasLocationPermission,
        _isOrderPlaced
    ){
        products, location, paymentMethod, permissionGranted, orderPlaced ->
        val subtotal= products.sumOf { (it.productId.price * it.quantity).toDouble() }
        val shipping= if(products.isNotEmpty()) 35.0 else 0.0
        val total = subtotal+shipping

        val address = if(location != null){
            "Lat: ${String.format(Locale.US, "%.4f", location.latitude)}, Lng:${String.format(Locale.US,"%.4f",location.longitude)}"
        }else if(permissionGranted){
            "Obteniendo ubicación actual...."
        }else{
            "Permiso de ubicación no concedido"
        }
        CheckoutUiState(
            cartProducts = products,
            subtotal=subtotal,
            shippingFee = shipping,
            total=total,
            userLocation = location,
            locationAddres = address,
            selectedPaymentMehtod = paymentMethod,
            hasLocationPermission = permissionGranted,
            isOrderPlaced = orderPlaced
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CheckoutUiState()
    )
    fun updateLocationPermission(isGranted: Boolean){
        _hasLocationPermission.value = isGranted
    }

    fun updateUserLocation(latLng: LatLng){
        _userLocation.value = latLng
    }
    fun selectPaymentMethod(method: String){
        _selectedPaymentMethod.value = method
    }

    fun confirmOrder(){
        _isOrderPlaced.value=true
    }
}