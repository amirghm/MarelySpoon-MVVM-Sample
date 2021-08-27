package com.amirghm.marelyspoon.annotations


@Target(AnnotationTarget.TYPE, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ContentType {
    companion object {
        const val CONTENT_TYPE_RECIPE = "recipe"
    }
}
