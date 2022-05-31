package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.presentation.model.FixturesPerLeagueModel

@Composable
fun FixturesPerDay(
    fixtures: List<FixturesPerLeagueModel>,
    onHeaderClick: (FixturesPerLeagueModel) -> Unit,
    onPredictionClick: (FixtureEntity) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
        items(fixtures) { fixtures: FixturesPerLeagueModel ->
            FixturesPerLeague(
                fixtures = fixtures,
                onHeaderClick = { fixturesPerLeagueModel -> onHeaderClick(fixturesPerLeagueModel) },
                onPredictionClick = { fixtureEntity -> onPredictionClick(fixtureEntity) }
            )
        }
    }
}
