package com.hyunjine.wt_running.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    @IdRes private val containerId: Int
) : AppCompatActivity() {
    protected lateinit var binding: T
    protected lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

//        navController = (supportFragmentManager.findFragmentById(containerId) as NavHostFragment).navController
    }

    protected fun showToast(@StringRes strId: Int) {
        Toast.makeText(this, strId, Toast.LENGTH_SHORT).show()
    }
}