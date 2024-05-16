package co.soyaeeb.nexgoprinter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.soyaeeb.nexgoprinter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var nexgoPrinter: NexgoPrinter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nexgoPrinter = NexgoPrinter(this)
        nexgoPrinter.initPrinter()

        binding.printTextBtn.setOnClickListener { printText() }
        binding.printQrCideBtn.setOnClickListener { printQrCode() }
        binding.printImageBtn.setOnClickListener { printImage() }
    }


    private fun printText() {
        nexgoPrinter.appendText("Testing English Text Print")
        nexgoPrinter.appendText("Testing English Text Print")
        nexgoPrinter.startPrint()

    }

    private fun printQrCode(){}

    private fun printImage(){}

}