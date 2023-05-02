package com.secui.healthone.ui.mealplanpage.ExerciseInput

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import android.icu.util.Calendar
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.secui.healthone.ui.mealplanpage.DatePickerDialog

@Composable
fun ExerciseInputDate() {
    var showDialog by remember { mutableStateOf(false) }
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 달력 아이콘과 날짜를 가운데에 배치
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 달력 아이콘
            IconButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Select Date")
            }

            // 선택된 날짜 표시
            Text(
                text = "${selectedDate.value.get(Calendar.YEAR)}.${selectedDate.value.get(Calendar.MONTH) + 1}.${
                    selectedDate.value.get(
                        Calendar.DAY_OF_MONTH
                    )
                }",
                textAlign = TextAlign.Center
            )
        }

        DatePickerDialog(
            showDialog = showDialog,
            initialDate = initialDate,
            onDateSelected = { newDate ->
                // 날짜 선택 이벤트 처리
                selectedDate.value = newDate
                showDialog = false
            },
            onDismissRequest = {
                showDialog = false
            }
        )
    }
}