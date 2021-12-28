package com.matusek.designer.product.domain

class Product (val id: Int, val name: String) {
    fun calculatePrice(): Double {
        return 1 * 12.3
    }
}