package zs.qimai.com.widget
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import zs.qimai.com.dialog.DialogManager
import zs.qimai.com.dialog.WlDialogFragment

class MainActivity : AppCompatActivity() {

    val dialogManager = DialogManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_default.setOnClickListener {
                //.show()
            dialogManager.pushDialogToQueue(WlDialogFragment.Builder(supportFragmentManager)
                .setDialogDismiss(DialogInterface.OnDismissListener {
                    Log.d(TAG, "onDismiss: ") })
                .create(),"1")
            dialogManager.pushDialogToQueue(WlDialogFragment.Builder(supportFragmentManager)
                .setDialogDismiss(DialogInterface.OnDismissListener {
                    Log.d(TAG, "onDismiss: ") })
                .create(),"2")
        }
        bt_customView.setOnClickListener {
         /*   WlDialogFragment.Builder(supportFragmentManager)
                .setLayoutRes(R.layout.test_dialog)
                .addViewClickId(R.id.tv_ok,R.id.tv_cancel)
                .setItemViewClick(object :OnViewClickListener{
                    override fun onViewClick(v: View, qmDialogFragment: WlDialogFragment) {
                        when(v.id){
                            R.id.tv_ok ->{
                                Toast.makeText(this@MainActivity,"ok",Toast.LENGTH_SHORT).show()
                            }
                            R.id.tv_cancel ->{
                                Toast.makeText(this@MainActivity,"cancel",Toast.LENGTH_SHORT).show()

                            }

                        }
                    }

                })
                .create()
                .show()*/
        }

    }
    companion object{
        private const val TAG = "MainActivity"

    }
}
