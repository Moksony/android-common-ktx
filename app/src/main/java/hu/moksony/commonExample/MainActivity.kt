package hu.moksony.commonExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.moksony.commonExample.databinding.ActivityMainBinding
import hu.moksony.android_common.viewBinding

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}