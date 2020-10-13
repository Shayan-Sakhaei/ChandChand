package com.android.chandchand.presentation.fixtures

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.android.chandchand.R
import com.android.chandchand.databinding.HeaderViewBinding
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.utils.roundedColoredStrokeBackground
import com.android.chandchand.presentation.utils.toPxf
import com.google.android.material.card.MaterialCardView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class FixtureHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = R.style.CustomMaterialCardView
) : MaterialCardView(context, attrs, defStyle) {

    private val binding = HeaderViewBinding.inflate(LayoutInflater.from(context), this)

    lateinit var leagueModel: LeagueModel
        @ModelProp set
    var onHeaderExpanded: OnClickListener? = null
        @CallbackProp set

    @AfterPropsSet
    fun bind() {
        binding.ivLeagueLogo.setImageResource(leagueModel.leagueLogo)
        binding.tvLeagueTitle.text = context.getString(leagueModel.leagueTitle)
        binding.tvFixturesCount.text = leagueModel.fixturesCount.toString()
        binding.ivDropDownArrowIcon.setImageResource(
            if (leagueModel.isExpanded) R.drawable.ic_drop_down_arrow_up_24
            else R.drawable.ic_drop_down_arrow_down_24
        )
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        useCompatPadding = true
        cardElevation = 4.toPxf()
        radius = 12.toPxf()
        setOnClickListener { onHeaderExpanded?.onClick(this) }
        binding.tvFixturesCount.roundedColoredStrokeBackground(
            8,
            context,
            R.color.colorWhite,
            R.color.colorPrimary
        )
    }
}