package zs.qimai.com.dialog

import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class DialogController {
    lateinit var fragmentManager: FragmentManager
    var layoutResId: Int = 0
    var width: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    var height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    var gravity: Int = 0
    var onViewClickListener: OnViewClickListener? = null
    var ids: IntArray? = null
    var paramsMap: MutableMap<Int, String>? = null
    var dismissListener: DialogInterface.OnDismissListener? = null
    var positive: (View, DialogFragment) -> Unit = { a, b -> }
    var negative: (View, DialogFragment) -> Unit = { a, b -> }
    var dialogTheme: Int = R.style.QmDialogTheme
    var onViewInflateFinish: WlDialogFragment.OnViewInflateFinish? = null
    var canceledOnTouchOutside = true
    var cancelable = true

    class Params {
        lateinit var fragmentManager: FragmentManager
        var layoutResId: Int = R.layout.ls_dialog_tips_layout
        var width: Int = ViewGroup.LayoutParams.WRAP_CONTENT
        var height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
        var gravity: Int = 0
        var onViewClickListener: OnViewClickListener? = null
        var ids: IntArray? = null
        var paramsMap: MutableMap<Int, String> = mutableMapOf()
        var dismissListener: DialogInterface.OnDismissListener? = null
        var positive: (View, DialogFragment) -> Unit = { a, b -> }
        var negative: (View, DialogFragment) -> Unit = { a, b -> }
        var dialogTheme: Int = R.style.QmDialogTheme
        var onViewInflateFinish: WlDialogFragment.OnViewInflateFinish? = null
        var canceledOnTouchOutside = true
        var Cancelable = true
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
            dialogController.onViewInflateFinish = this.onViewInflateFinish
            dialogController.cancelable = this.Cancelable
            dialogController.canceledOnTouchOutside = this.canceledOnTouchOutside
        }
    }

}