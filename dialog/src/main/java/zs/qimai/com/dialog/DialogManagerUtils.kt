package zs.qimai.com.dialog

import android.app.Activity

 class DialogManagerUtils private constructor(){

    private val mHashLinkedList by lazy {
        HashMap<Activity, DialogManager>()
    }
    private var mDialogManagerUtils: DialogManagerUtils? = null
    /***
     *
     * 创建全局唯一实例
     * */
     fun test(): DialogManagerUtils {
        if (mDialogManagerUtils == null) {
            mDialogManagerUtils = DialogManagerUtils()
        }
        return mDialogManagerUtils!!
    }

    /****
     * 传入Activity 获取DialogManger  确保同一个Activity能有一个管理类，能按顺序排列
     * @param activity Activity
     * @return DialogManager?
     */
    fun getDialogManager(activity: Activity): DialogManager? {
        return if (mHashLinkedList.containsKey(activity)) {
            mHashLinkedList[activity]
        } else {
            val manager = DialogManager()
            mHashLinkedList[activity] = manager
            manager
        }
    }

     companion object{
         val instance :DialogManagerUtils by lazy {
             DialogManagerUtils()
         }

     }



}