package com.example.cdcapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cdcapplication.domain.BankTransfer
import com.example.cdcapplication.domain.BankTransferType
import com.example.cdcapplication.extensions.round
import kotlin.random.Random

@Composable
fun BankTransferCard(bankTransfer: BankTransfer) {
    Divider(color =  Color(0xffcccccc), thickness = 1.dp)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp),
    ) {
        val (icon, text, amount, accounted) = createRefs()

        Text(text = if(!bankTransfer.isAccounted) "NOT ACCOUNTED" else "",
            fontSize = 11.sp,
            color = Color(0xff59b1c7),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(accounted){
                absoluteRight.linkTo(parent.absoluteRight)
                top.linkTo(parent.top)
                bottom.linkTo(amount.top)
            })

        Column(
            modifier = Modifier.constrainAs(icon){
                absoluteLeft.linkTo(parent.absoluteLeft)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        when (bankTransfer.bankTransferType) {
                            BankTransferType.HOME -> Color(0xfff2e7f7)
                            BankTransferType.FOOD -> Color(0xffe6f3fb)
                            BankTransferType.TRAVEL -> Color(0xfffcefe9)
                        }
                    )
                    .alpha(0.3f)
            )
        }

        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp, 0.dp, 0.dp)
                .constrainAs(text) {
                    absoluteLeft.linkTo(icon.absoluteRight)
                }
        ) {
            Text(text = bankTransfer.date, color = Color.Black, fontSize = 11.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = bankTransfer.description, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = "Conto n." + bankTransfer.bankAccount, fontSize = 12.sp, color = Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Column(
            modifier = Modifier
                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                .constrainAs(amount) {
                    absoluteRight.linkTo(parent.absoluteRight)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ){
            Text(text =
            when(Random.nextBoolean()){
                true -> ""
                false -> "-"
            }
                    + bankTransfer.amount.round(2).toString() + " €", fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }

    }
}
