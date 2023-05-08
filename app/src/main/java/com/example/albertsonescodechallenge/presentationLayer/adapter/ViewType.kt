package com.example.albertsonescodechallenge.presentationLayer.adapter

import com.example.albertsonescodechallenge.dataLayer.model.Lf
import com.example.albertsonescodechallenge.dataLayer.model.Var

sealed class ViewType {
    data class LF_VIEW(val lf: Lf) : ViewType()
    data class VAR_VIEW(val variant: Var) : ViewType()
}
