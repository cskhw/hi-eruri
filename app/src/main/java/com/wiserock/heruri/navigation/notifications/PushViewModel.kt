package com.wiserock.heruri.navigation.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PushViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "푸시 알람 만들 예정입니다 ㅠㅠㅠ\n 있었으면 좋겠는 기능들 플레이스토어에서\n" +
                "별점 5개와 함께 적어주시면 \n매우매우 감사드리겠읍니다!!!"
    }
    val text: LiveData<String> = _text
}