package ru.lachesis.translator.model.repository

import io.reactivex.Observable
import io.reactivex.Single

interface Repository<T> {
    fun getData(word: String): Observable<T>
}