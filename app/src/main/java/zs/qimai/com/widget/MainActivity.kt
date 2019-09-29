package zs.qimai.com.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_default.setOnClickListener {
            WlDialogFragment.Builder(supportFragmentManager)
                .create()
                .show()

        }
        bt_customView.setOnClickListener {
            WlDialogFragment.Builder(supportFragmentManager)
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
                .show()
        }

    }
}
