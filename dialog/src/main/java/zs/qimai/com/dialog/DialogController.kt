package zs.qimai.com.dialog

import android.content.DialogInterface
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class DialogController {
    lateinit var fragmentManager: FragmentManager
    var layoutResId: Int = 0
    var width: Int = 0
    var height: Int = 0
    var gravity: Int = 0
    var onViewClickListener: OnViewClickListener? = null
    var ids: IntArray? = null
    var paramsMap: MutableMap<Int, String>? = null
    var dismissListener: DialogInterface.OnDismissListener? = null
     var positive: (View,DialogFragment) -> Unit = {a,b->}
     var negative: (View,DialogFragment) -> Unit = {a,b ->}
    var dialogTheme:Int = R.style.QmDialogTheme
    class Params {
        lateinit var fragmentManager: FragmentManager
        var layoutResId: Int = R.layout.ls_dialog_tips_layout
        var width: Int = 0
        var height: Int = 0
        var gravity: Int = 0
        var onViewClickListener: OnViewClickListener? = null
        var ids: IntArray? = null
        var paramsMap: MutableMap<Int, String> = mutableMapOf()
         var dismissListener: DialogInterface.OnDismissListener? = null
        var positive: (View,DialogFragment) -> Unit = {a,b->}
        var negative: (View,DialogFragment) -> Unit = {a,b ->}
        var dialogTheme:Int = R.style.QmDialogTheme

        fun apply(dialogController: DialogController) {
            //dialogController.dialogView = this.dialogView
            dialogController.fragmentManager = this.fragmentManager
            dialogController.gravity = this.gravity
            dialogController.width = this.width
            dialogController.height = this.height
            dialogController.layoutResId = this.layoutResId
            dialogController.onViewClickListener = this.onViewClickListener
            dialogController.ids = this.ids
            dialogController.paramsMap = this.paramsMap
            dialogController.dismissListener = this.dismissListener
            dialogController.positive = this.positive
            dialogController.negative = this.negative
            dialogController.dialogTheme = this.dialogTheme
        }
    }

}