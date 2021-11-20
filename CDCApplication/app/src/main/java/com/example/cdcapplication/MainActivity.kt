package com.example.cdcapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cdcapplication.domain.BankAccount
import com.example.cdcapplication.domain.BankTransfer
import com.example.cdcapplication.component.Arch
import com.example.cdcapplication.component.BankAccountCard
import com.example.cdcapplication.component.BankTransferCard
import com.example.cdcapplication.component.Pager
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            homePage(getBankAccount(5), getBankTransfers(23))
        }
    }
}

fun getBankTransfers(count: Int) : List<BankTransfer>{
    val bankTransfers = mutableListOf<BankTransfer>()
    for ( i in 0..count){
        bankTransfers.add(BankTransfer())
    }
    return bankTransfers
}

fun getBankAccount(count: Int) : List<BankAccount>{
    val bankAccounts = mutableListOf<BankAccount>()
    for ( i in 0..count){
        bankAccounts.add(BankAccount())
    }
    return bankAccounts
}

@Composable
fun homePage(bankAccounts: List<BankAccount>, bankTransfers: List<BankTransfer>){
    var yValue by remember {
        mutableStateOf(0f)
    }
    var bottomY by remember {
        mutableStateOf(0f)
    }
    var screenSize = 0f

    val lazyListState = rememberLazyListState()

    val nestedScrollDispatcher = remember { NestedScrollDispatcher() }

    val nestedScrollConnection = object : NestedScrollConnection{
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val offset = available.y
            if((yValue + offset) < 0)
                yValue += offset
            return Offset.Zero
        }
    }

    BoxWithConstraints {
        screenSize = this.maxHeight.value

        Log.d("[ dragOffsetY ]", "dragOffsetY: $yValue")
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection, nestedScrollDispatcher)
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState{ delta ->
                        if((yValue + delta) < 0)
                            yValue += delta
                    }
                )
        ) {

            val (anchor, arch, carousel, data) = createRefs()


            val modifier = Modifier.constrainAs(data) {
                top.linkTo(anchor.bottom)
                bottom.linkTo(parent.bottom)
            }

            BankTransfers(modifier, bankTransfers, yValue, bottomY, lazyListState)

            Box(
                modifier = Modifier
                    .height(280.dp)
                    .constrainAs(anchor) {
                        top.linkTo(parent.top)
                    }
                    .offset(0.dp, yValue.dp)
            )
            Box(
                modifier = Modifier
                    .constrainAs(arch) {
                        top.linkTo(parent.top)
                    }
                    .offset(0.dp, yValue.dp)

            ) {
                Arch()
            }
            Box (
                modifier = Modifier.constrainAs(carousel){
                    top.linkTo(parent.top)
                }
            ) {
                Carousel(bankAccounts, yValue)
            }
        }
    }

}

@Composable
fun Carousel(items: List<BankAccount>, offset: Float){
    Pager(
        items = items,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 30.dp, 0.dp, 30.dp)
            .graphicsLayer {
                translationY = offset
                alpha = 1 + offset / 200
            },
        itemFraction = .95f,
        overshootFraction = .01f,
        initialIndex = 1,
        itemSpacing = 26.dp,
    ) {
        items.forEachIndexed { _, item ->
            BankAccountCard(item = item)
        }
    }
}

@Composable
fun BankTransfers(modifier: Modifier, bankTransfers: List<BankTransfer>, dragOffsetY: Float, bottom: Float, lazyListState: LazyListState ){

    LazyColumn(
        modifier = modifier
            .offset(0.dp, dragOffsetY.dp)
        ,
        state = lazyListState
    ) {
        itemsIndexed(bankTransfers)  { index , current  ->
            when(index){
                0 -> {
                    Text(text = "Operazioni",
                        fontSize = 21.sp,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(20.dp, 45.dp, 0.dp, 10.dp)
                    )
                }
                else -> BankTransferCard(current)
            }
        }
    }
}
