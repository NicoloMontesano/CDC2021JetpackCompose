package com.example.cdcapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cdcapplication.domain.BankAccount
import com.example.cdcapplication.extensions.round

@Composable
fun BankAccountCard(item: BankAccount){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(25.dp, 25.dp, 0.dp, 0.dp),
                text = "Conto " + item.bankAccountNumber, fontSize = 18.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(
                modifier = Modifier.padding(25.dp, 5.dp, 0.dp, 0.dp),
                text = item.balance.round(2).toString() + " €", fontSize = 25.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.padding(25.dp, 5.dp, 0.dp, 20.dp),
                text = "Dettagli", fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color(0xff228109)
            )
            Divider(color =  Color(0xffcccccc), thickness = 1.dp)
            Text(
                modifier = Modifier.padding(15.dp, 15.dp, 0.dp, 15.dp),
                text = item.iban, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xffc4e5d0))) {
                Text(
                    modifier = Modifier.padding(15.dp, 15.dp, 0.dp, 15.dp),
                    text = "Attiva SmartMoney", fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}