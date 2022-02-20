package com.betelguese.ktorproject.commons

sealed class Resource <T>(val data:T? = null,val message:String?=null){
    class Error<T>(message: String) : Resource<T>(data=null,message=message)
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T>(data: T? = null):Resource<T>(null,null)
}