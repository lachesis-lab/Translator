package ru.lachesis.translator.model.repository

import io.reactivex.Single

interface Repository<T> {
    fun getData(word: String): Single<T>
}