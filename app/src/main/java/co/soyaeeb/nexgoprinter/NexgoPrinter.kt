package co.soyaeeb.nexgoprinter


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Typeface
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.DotMatrixFontEnum
import com.nexgo.oaf.apiv3.device.printer.FontEntity
import com.nexgo.oaf.apiv3.device.printer.LineOptionEntity
import com.nexgo.oaf.apiv3.device.printer.Printer

class NexgoPrinter(context: Context) {

    private val deviceEngine: DeviceEngine = APIProxy.getDeviceEngine(context)
    private val printer: Printer = deviceEngine.printer

     fun initPrinter(){
        printer.initPrinter()
        printer.setTypeface(Typeface.DEFAULT)
     }

    companion object {
        val fontSmall = FontEntity(DotMatrixFontEnum.CH_SONG_20X20, DotMatrixFontEnum.ASC_SONG_8X16)
        val fontNormal = FontEntity(DotMatrixFontEnum.CH_SONG_24X24, DotMatrixFontEnum.ASC_SONG_12X24)
        val fontBold = FontEntity(DotMatrixFontEnum.CH_SONG_24X24, DotMatrixFontEnum.ASC_SONG_BOLD_16X24)
        val fontBig = FontEntity(DotMatrixFontEnum.CH_SONG_24X24, DotMatrixFontEnum.ASC_SONG_12X24, false, true)

        val alignmentLeft = AlignEnum.LEFT
        val alignmentRight = AlignEnum.RIGHT
        val alignmentCenter = AlignEnum.CENTER
    }

    fun isPrintSuccess() = printer.status == SdkResult.Success

    fun setLineSpace(lineSpace: Int){
        printer.setLetterSpacing(lineSpace)
    }

    fun appendText(
        text: String,
        fontSize: FontEntity = fontNormal,
        alignment: AlignEnum = alignmentLeft,
        isUnderLine: Boolean = false,
        isBold: Boolean = false
    ){
        val lineOptionEntity = LineOptionEntity.Builder().setBold(isBold).setUnderline(isUnderLine).build()
        printer.appendPrnStr(text, fontSize, alignment, lineOptionEntity)
    }

    fun appendQRCode(
        value: String,
        alignment: AlignEnum = alignmentLeft,
        height: Int = 200,
        version: Int = 10, // version can lie between 1-40
        level: Int = 3 // level can lie between 1-4
    ){
        printer.appendQRcode(value,height,version,level,alignment)
    }

    fun appendImage(
        bitmap: Bitmap,
        alignment: AlignEnum = alignmentLeft
    ){
       printer.appendImage(bitmap, alignment)
    }

    fun startPrint() {
        printer.startPrint(true){}
    }
}