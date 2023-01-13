package com.hyunjine.wt_running.view.login

import android.os.Bundle
import com.hyunjine.domain.util.loggerE
import com.hyunjine.wt_running.R
import com.hyunjine.wt_running.base.BaseActivity
import com.hyunjine.wt_running.databinding.ActivityLoginBinding
import com.naver.maps.map.NaverMapSdk

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login, R.id.nav_graph_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NaverMapSdk.OnAuthFailedListener {
            loggerE("con", it)
        }
    }

}