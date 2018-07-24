package com.genaku.remote

import com.genaku.remote.UrlUtils.Companion.getParamValue
import com.genaku.remote.jsonmodel.Post
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import org.junit.runner.RunWith
import pl.mareklangiewicz.uspek.USpek.uspek
import pl.mareklangiewicz.uspek.USpek.o
import pl.mareklangiewicz.uspek.USpekJUnitRunner
import pl.mareklangiewicz.uspek.eq
import java.util.*

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
@RunWith(USpekJUnitRunner::class)
class JSONParseTest {
    init {
        uspek("parse test") {
            "test get param value" o {
                val url = "https://oauth.vk.com/blank.html#access_token=0faca817f52778c1c0b3cb66707a8dc1c30ef338dee488b1fb26f1d85e6541e095e485ab29a844b5b856c&expires_in=0&user_id=1551084"
                val s = getParamValue(url, AuthConst.TOKEN_PARAM)
                s eq "0faca817f52778c1c0b3cb66707a8dc1c30ef338dee488b1fb26f1d85e6541e095e485ab29a844b5b856c"
            }
        }
    }

}