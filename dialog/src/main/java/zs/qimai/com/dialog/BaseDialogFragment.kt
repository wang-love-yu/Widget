package zs.qimai.com.dialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {
    private var containerView: View? = null
    abstract fun getLayoutId(): Int
    abstract fun bindView(v: View)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.QmDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        containerView = layoutInflater.inflate(getLayoutId(), container, false)
        // return super.onCreateView(inflater, container, savedInstanceState)
        bindView(containerView!!)
        return containerView
    }

    override fun onStart() {
        super.onStart()
        val windowManager = dialog!!.window
        //设置宽高
        val layoutParams = windowManager?.attributes
        //设置gravity
        layoutParams?.gravity = getGravity()
        windowManager?.attributes = layoutParams
        windowManager?.setLayout(getDialogWidth(), getDialogHeight())
    }

    fun <T : View> getView(id: Int): T? {
        return containerView?.findViewById<T>(id)
    }

    open fun getGravity() = Gravity.CENTER
    open fun getDialogWidth() = WindowManager.LayoutParams.WRAP_CONTENT
    open fun getDialogHeight() = WindowManager.LayoutParams.WRAP_CONTENT
    open fun isCanCanceldOnTouch() = true

}