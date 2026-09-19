package com.example.aplicaciontaller.View

import android.icu.text.CaseMap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplicaciontaller.Components.CardViewHome
import com.example.aplicaciontaller.Components.CartButton
import com.example.aplicaciontaller.Components.MainScaffold
import com.example.aplicaciontaller.Components.ProductCardHome
import com.example.aplicaciontaller.Components.SearchBarComponent
import com.example.aplicaciontaller.Components.Title
import com.example.aplicaciontaller.Components.cardCategoryHome
import com.example.aplicaciontaller.Components.spaceH
import com.example.aplicaciontaller.Components.spaceV
import com.example.aplicaciontaller.Data.database.AppDatabase
import com.example.aplicaciontaller.Data.repository.CartRepository
import com.example.aplicaciontaller.Data.repository.CategoryRepository
import com.example.aplicaciontaller.Data.repository.ProductRepository
import com.example.aplicaciontaller.R
import com.example.aplicaciontaller.ViewModel.CartViewModel
import com.example.aplicaciontaller.ViewModel.CartViewModelFactory
import com.example.aplicaciontaller.ViewModel.HomeViewModel
import com.example.aplicaciontaller.ViewModel.HomeViewModelFactory
import com.example.aplicaciontaller.ViewModel.ProductViewModel
import com.example.aplicaciontaller.ViewModel.ProductViewModelFactory
import com.example.aplicaciontaller.ui.theme.AplicacionTallerTheme
import com.example.aplicaciontaller.ui.theme.ultra_l_blue
import com.example.aplicaciontaller.ui.theme.ultra_l_green
import com.example.aplicaciontaller.ui.theme.ultra_l_purple
import com.example.aplicaciontaller.ui.theme.ultra_l_red
import com.example.aplicaciontaller.ui.theme.ultra_l_yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit,
    onCartClick : () -> Unit = {}
){
    MainScaffold(
        selectedItem = 0,
        onItemSelected = onItemSelected,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Title(text = "Home View")
                    }
                    //colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = MaterialTheme.colorScheme.Red
                )
                //SearchBarComponent()
            }
        },
        floatingActionButton = {
            CartButton(onClick = onCartClick)
        }
    ) {padding ->
        ContentHomeView(Modifier.padding(padding))
    }
}

@Composable
fun ContentHomeView(
    modifier: Modifier = Modifier
){
    val context= LocalContext.current
    val database = AppDatabase.getDatabase(context)

    val categoryDao = database.categoryDao()

    //Repositorios
    val categoryRepository = CategoryRepository(categoryDao)
    val productRepository= ProductRepository(database.productDao())
    val cartRepository= CartRepository(database.cartItemDao())

    //ViewModels
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(categoryRepository)
    )
    val productViewModel: ProductViewModel =
        viewModel(factory = ProductViewModelFactory(productRepository))
    val cartViewModel: CartViewModel=
        viewModel(factory = CartViewModelFactory(cartRepository,productRepository))

    //Estados
    val categories by homeViewModel.categories.collectAsState()
    val products by productViewModel.products.collectAsState()

    Column(
        modifier=Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CardViewHome(
            "Frutas y verduras frescas",
            "20% de descuento en tu primera compra",
            stringResource(R.string.texto_boton),
            R.drawable.manzana
        )
        spaceV(16)
        SectionHeader("Categorías")
        LazyRow(
            modifier=Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(categories){ category ->
                cardCategoryHome(
                    categoria= category.name,
                    imagen = category.image,
                    color = category.color
                )
                spaceH(6)
            }
        }
        spaceV(16)
        SectionHeader("Productos destacados")
        spaceV(8)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products){product ->
                ProductCardHome(
                    product=product,
                    onAddClick = {cartViewModel.addToCart(product.id)}
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Ver todas",
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    AplicacionTallerTheme{
        HomeView(
            modifier = Modifier,
            onItemSelected = { }
        )
    }
}