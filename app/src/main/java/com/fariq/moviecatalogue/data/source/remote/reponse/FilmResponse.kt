package com.fariq.moviecatalogue.data.source.remote.reponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmResponse (
        var id : String = "",
        var title : String = "",
        var image : String = "",
        var desc : String = "",
        var genre : String = "",
        var year : Int = 0,
        var favorite : Boolean = false,
        var type : String = ""
) : Parcelable