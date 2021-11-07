package com.example.cdcapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cdcapplication.domain.BankAccount
import com.example.cdcapplication.domain.BankTransfer
import com.example.cdcapplication.domain.BankTransferType
import com.example.cdcapplication.extensions.round
import com.example.cdcapplication.ui.Arch
import com.example.cdcapplication.ui.BankAccountCard
import com.example.cdcapplication.ui.BankTransferCard
import com.example.cdcapplication.ui.Pager
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            homePage()
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
fun homePage(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (anchor, arch, carousel, data) = createRefs()
        Box (
            modifier = Modifier.constrainAs(data){
                top.linkTo(anchor.bottom)
            }
        ) {
            BankTransfers(getBankTransfers(22))
        }
        Box(
            modifier = Modifier
                .height(280.dp)
                .constrainAs(anchor) {
                    top.linkTo(parent.top)
                }
        )
        Box(
            modifier = Modifier.constrainAs(arch){
                top.linkTo(parent.top)
            }
        ) {
            Arch()
        }
        Box (
            modifier = Modifier.constrainAs(carousel){
                top.linkTo(parent.top)
            }
        ) {
            Carousel(getBankAccount(4))
        }
    }

}

@Composable
fun Carousel(items: List<BankAccount>){
    Pager(
        items = items,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 30.dp, 0.dp, 30.dp),
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
fun BankTransfers(bankTransfers: List<BankTransfer>){
    LazyColumn {
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