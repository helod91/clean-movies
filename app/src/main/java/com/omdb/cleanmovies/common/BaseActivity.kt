package com.omdb.cleanmovies.common

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.omdb.cleanmovies.models.Data
import com.omdb.cleanmovies.models.Status
import io.reactivex.functions.Consumer
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<V : ViewDataBinding, M : BaseViewModel>(
    private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: V
    protected lateinit var viewModel: M

    protected open var showBackButton = false

    abstract fun viewModelClass(): KClass<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel(viewModelClass())

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()

        binding.unbind()
    }

    protected fun <RequestData> observeSuccess(
        liveData: LiveData<Data<RequestData>>,
        progress: ProgressBar? = null,
        successAction: Consumer<RequestData>
    ) {
        liveData.observe(this, Observer {
            progress?.visibility = View.GONE

            when (it.responseType) {
                Status.LOADING -> progress?.visibility = View.VISIBLE
                Status.SUCCESSFUL -> successAction.accept(it.data)
                Status.ERROR ->
                    AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage(it.error?.message)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()

            }
        })
    }
}