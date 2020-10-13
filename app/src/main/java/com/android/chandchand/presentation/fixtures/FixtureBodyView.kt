package com.android.chandchand.presentation.fixtures

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.android.chandchand.R
import com.android.chandchand.databinding.BodyViewBinding
import com.android.chandchand.presentation.utils.*
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class FixtureBodyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = R.style.CustomMaterialCardView
) : MaterialCardView(context, attrs, defStyle) {

    private val binding = BodyViewBinding.inflate(LayoutInflater.from(context), this)

    var homeLogoUrl: String? = null
        @ModelProp set
    var awayLogoUrl: String? = null
        @ModelProp set
    var homeName: String? = null
        @ModelProp set
    var awayName: String? = null
        @ModelProp set
    var homeGoals: String? = null
        @ModelProp set
    var awayGoals: String? = null
        @ModelProp set
    var status: String? = null
        @ModelProp set
    var time: Long? = null
        @ModelProp set

    @AfterPropsSet
    fun bind() {
        Glide.with(context).load(homeLogoUrl).into(binding.ivHomeLogo)
        Glide.with(context).load(awayLogoUrl).into(binding.ivAwayLogo)
        binding.tvHomeName.text = homeName
        binding.tvAwayName.text = awayName

        when (status) {
            NOT_STARTED -> {
                binding.tvMatchTime.text = time?.toHourMin()
            }
            MATCH_POSTPONED -> {
                binding.tvMatchTime.text = ""
                binding.tvMatchStatus.text = context.getString(R.string.postponed)
                binding.tvHomeGoals.text = "?"
                binding.tvAwayGoals.text = "?"
                binding.tvMatchStatus.isVisible = true
                binding.tvHomeGoals.isVisible = true
                binding.tvAwayGoals.isVisible = true
            }
            MATCH_CANCELLED -> {
                binding.tvMatchTime.text = ""
                binding.tvMatchStatus.text = context.getString(R.string.cancelled)
                binding.tvHomeGoals.text = "_"
                binding.tvAwayGoals.text = "_"
                binding.tvMatchStatus.isVisible = true
                binding.tvHomeGoals.isVisible = true
                binding.tvAwayGoals.isVisible = true
            }
            MATCH_FINISHED -> {
                binding.tvMatchTime.text = time?.toHourMin()
                binding.tvMatchTime.paintFlags =
                    binding.tvMatchTime.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvMatchStatus.text = context.getString(R.string.match_finished)
                binding.tvHomeGoals.text = homeGoals
                binding.tvAwayGoals.text = awayGoals
                binding.ivPredictionIcon.isEnabled = false
                binding.tvMatchStatus.isVisible = true
                binding.tvHomeGoals.isVisible = true
                binding.tvAwayGoals.isVisible = true
            }
            else -> {
                binding.tvMatchTime.text = time?.toHourMin()
                binding.tvMatchStatus.text = context.getString(R.string.ongoing)
                binding.tvHomeGoals.text = homeGoals
                binding.tvAwayGoals.text = awayGoals
                binding.tvMatchStatus.isVisible = true
                binding.tvHomeGoals.isVisible = true
                binding.tvAwayGoals.isVisible = true
            }
        }
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        useCompatPadding = true
        cardElevation = 4.toPxf()
        radius = 12.toPxf()
    }
}