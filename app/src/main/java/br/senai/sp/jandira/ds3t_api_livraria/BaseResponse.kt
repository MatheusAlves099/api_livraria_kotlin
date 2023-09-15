package br.senai.sp.jandira.ds3t_api_livraria

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    var data: T? = null
)