package com.bignerdranch.android.geoguiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);

        trueButton.setOnClickListener{ view : View ->
            var toast = Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, Gravity.CENTER, 550);
            toast.show();
        }
        falseButton.setOnClickListener{ view : View ->
            var toast = Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, Gravity.CENTER, 550);
//             JAVA 11, API 30 은 setGravity 옵션이 없어 미적용 API 28 이하로 낮추거나 Snakbar 사용 권장
            toast.show();

            // Snackbar
//            var snackbar = Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT);
//            snackbar.setAction("show Toast"){
//                val toastFalse = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
//                toastFalse.setGravity(Gravity.TOP, Gravity.CENTER, 550);
//                toastFalse.show();
//            }.show()
        }
    }
}