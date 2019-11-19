package zs.qimai.com.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference

class WlDialogFragment : BaseDialogFragment() {
    var dialogController: DialogController = DialogController()
    private var mDismissListener: DialogInterface.OnDismissListener? = null
    private var mContainerListnerList: ArrayList<WeakReference<View>>? = null
    var mDialogTheme = R.style.QmDialogTheme
    override fun getLayoutId() = dialogController.layoutResId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, mDialogTheme)
    }

    override fun onDismiss(dialog: DialogInterface) {
        this.dialog?.setOnShowListener(null)
        this.dialog?.setOnCancelListener(null)
        this.dialog?.setOnDismissListener(null)
        super.onDismiss(dialog)
        mDismissListener?.onDismiss(dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = super.onCreateView(inflater, container, savedInstanceState)
        dialogController?.onViewInflateFinish?.onViewInflate(view)
        return view
    }

    override fun bindView(v: View) {
        //设置取消，确认按钮监听事件
        getDialogChildView<View>(R.id.tv_cancel, v)
            ?.setOnClickListener {
                dialogController.negative(it, this)
                dismiss()
            }

        getDialogChildView<View>(R.id.tv_ok, v)?.setOnClickListener {
            dialogController.positive(it, this)
            dismiss()
        }
        //绑定监听
        if (dialogController != null && dialogController.ids != null && dialogController.ids!!.isNotEmpty()) {

            for (id in dialogController.ids!!) {
                if (mContainerListnerList == null) {
                    mContainerListnerList = ArrayList()
                }
                getDialogChildView<View>(id, v)?.let {
                    mContainerListnerList?.add(WeakReference<View>(it))
                    it.setOnClickListener { v ->
                        if (dialogController.onViewClickListener != null) {
                            dialogController.onViewClickListener!!.onViewClick(
                                v!!,
                                this@WlDialogFragment
                            )

                        }
                    }
                }
            }
        }
        //设置文字
        if (dialogController.paramsMap != null && dialogController.paramsMap!!.isNotEmpty()) {
            for (item in dialogController.paramsMap!!) {
                getDialogChildView<View>(item.key, v)?.let {
                    if (it is TextView) it.text = item.value
                    if (it is EditText) it.setText(item.value)
                }
            }
        }

    }


    private fun <T : View> getDialogChildView(id: Int, v: View): T? {
        val childView: View? = v.findViewById(id)
        return if (childView == null) null else childView as T
    }

    override fun getGravity(): Int {
        return dialogController.gravity
    }

    override fun getDialogWidth(): Int {
        return dialogController.width
    }

    override fun getDialogHeight(): Int {
        return dialogController.height
    }

    class Builder(private var fragmentManager: FragmentManager) {
        private var params: DialogController.Params = DialogController.Params()
        //控件名称改变
        private var paramsMap: MutableMap<Int, String>? = null
        var mDialogTheme = R.style.QmDialogTheme

        init {
            params.fragmentManager = this.fragmentManager
        }

        fun setGravity(gravity: Int): Builder {
            params.gravity = gravity
            return this
        }

        fun setOnDismissListener(listener: DialogInterface.OnDismissListener) {
            params.dismissListener = listener
        }

        fun setOnViewInflateListener(listener: OnViewInflateFinish) {

        }

        fun setWidth(width: Int): Builder {
            params.width = width
            return this
        }

        fun setHeight(height: Int): Builder {
            params.height = height
            return this
        }

        fun setLayoutRes(layout: Int): Builder {
            params.layoutResId = layout
            return this
        }

        fun addViewClickId(vararg ids: Int): Builder {
            params.ids = ids
            return this
        }


        fun create(): WlDialogFragment {
            val qmDialogFragment = WlDialogFragment()
            qmDialogFragment.mDismissListener = params.dismissListener
            params.apply(qmDialogFragment.dialogController)
            return qmDialogFragment
        }

        fun setItemViewClick(onViewClickListener: OnViewClickListener): Builder {
            params.onViewClickListener = onViewClickListener
            return this
        }

        fun withPositive(positive: (View, DialogFragment) -> Unit): Builder {
            params.positive = positive
            return this
        }

        fun withNegative(negative: (View, DialogFragment) -> Unit): Builder {
            params.negative = negative
            return this
        }

        fun setText(id: Int, text: String): Builder {
            if (paramsMap == null) {
                paramsMap = mutableMapOf()
            }
            //paramsMap!!.put(id,text)
            params.paramsMap[id] = text
            return this
        }

        fun setContent(text: String): Builder {
            if (paramsMap == null) {
                paramsMap = mutableMapOf()
            }
            //paramsMap!!.put(id,text)
            params.paramsMap[R.id.tv_content] = text
            return this
        }

        fun setTitle(text: String): Builder {
            if (paramsMap == null) {
                paramsMap = mutableMapOf()
            }
            //paramsMap!!.put(id,text)
            params.paramsMap[R.id.tv_title] = text
            return this
        }

        fun setDialogDismiss(dismissListener: DialogInterface.OnDismissListener): Builder {
            params.dismissListener = dismissListener
            return this
        }
    }

    fun show(tag: String = "default"): WlDialogFragment {
        dialogController.fragmentManager.beginTransaction()
            .add(this, tag)
            .commitAllowingStateLoss()
        //   show(fragmentManager)
        return this
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        this.dialog?.setOnShowListener(null)
        this.dialog?.setOnCancelListener(null)
        this.dialog?.setOnDismissListener(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mDismissListener = null
        mContainerListnerList?.forEach {
            it?.get()?.setOnClickListener(null)
        }
        mContainerListnerList?.clear()
        mContainerListnerList = null
        dialog?.setOnShowListener(null)
        dialog?.setOnCancelListener(null)
        dialog?.setOnDismissListener(null)
        if (dialog != null && dialog!!.isShowing()) {
            dialog!!.dismiss()
        }
    }

    interface OnViewInflateFinish {
        fun onViewInflate(view: View?)
    }

}