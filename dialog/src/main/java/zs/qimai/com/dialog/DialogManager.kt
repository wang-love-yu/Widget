package zs.qimai.com.dialog

import android.content.DialogInterface
import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue
/***
 *
 * Dialog管理类，管理多个Dialog按顺序出现
 *
 *
 * * **/
class DialogManager {
    private var isShowDialoging = false
    val mDialogQueue: ConcurrentLinkedQueue<DialogParams> by lazy {
        ConcurrentLinkedQueue<DialogParams>()
    }

    fun pushDialogToQueue(dialogFragment: WlDialogFragment, tag:String = "",priority: Int = 0) {
        if (dialogFragment != null) {
            var dialogParams = DialogParams(dialogFragment.apply {
                dialogController.mDismissListenerList.add(
                    object : OnDialogDismissListener {
                        override fun onDismiss(dialog: DialogInterface?) {
                            Log.d(TAG, "onDismiss: ")
                            isShowDialoging = false
                            startShowDialog()
                        }
                    })
            }, tag,priority)
            mDialogQueue.add(dialogParams)
        }
      /*  if (canShow()) {
            startShowDialog()
        }*/
    }

    fun startShowDialog() {
        if (mDialogQueue.isNullOrEmpty()) {
            return
        } else {
            prioritySequence(mDialogQueue)
            var dialogParams = mDialogQueue.poll()
            dialogParams?.let {
                it.dialogFragment?.show(it.tag)
                isShowDialoging = true
            }
        }
    }

    private fun prioritySequence(mDialogQueue: ConcurrentLinkedQueue<DialogManager.DialogParams>) {
        mDialogQueue.sortedBy {
            it.priority
        }
    }

    companion object {
        private const val TAG = "DialogManager"
    }
    //判断是否可以显示弹窗
    private fun canShow() = isShowDialoging
    class DialogParams(val dialogFragment: WlDialogFragment,var tag:String = "", var priority: Int = 0)

    //清除
    fun clear(){
        mDialogQueue?.forEach {
            if (it.dialogFragment!=null&& it.dialogFragment?.dialog?.isShowing == true){
                it.dialogFragment.dismiss()
            }
        }
        mDialogQueue.clear()
        //mDialogQueue = null
    }

}