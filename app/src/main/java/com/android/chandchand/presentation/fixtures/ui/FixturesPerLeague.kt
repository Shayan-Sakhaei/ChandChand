package com.android.chandchand.presentation.fixtures.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.chandchand.R
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.ui.PreviewData
import com.android.chandchand.presentation.ui.theme.ChandChandTheme
import com.android.domain.entities.FixtureEntity

@Composable
fun FixturesPerLeague(
    fixtures: FixturesPerLeagueModel,
    onHeaderClick: (FixturesPerLeagueModel) -> Unit,
    onPredictionClick: (FixtureEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(64.dp)
            .animateContentSize(animationSpec = tween()),
    ) {
        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
                .fillMaxWidth()
                .height(64.dp)
                .clickable { onHeaderClick(fixtures) },
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding(start = 16.dp),
                    painter = painterResource(
                        id = if (fixtures.isExpanded) R.drawable.ic_drop_down_arrow_up_24 else R.drawable.ic_drop_down_arrow_down_24
                    ),
                    contentDescription = "arrow_up_or_down"
                )

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(32.dp)
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        text = "${fixtures.leagueModel.fixturesCount}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Text(
                    text = stringResource(id = fixtures.leagueModel.leagueTitle),
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .weight(1f),
                    textAlign = TextAlign.End
                )

                Image(
                    modifier = Modifier.padding(end = 12.dp),
                    painter = painterResource(id = fixtures.leagueModel.leagueLogo),
                    contentDescription = "${stringResource(id = fixtures.leagueModel.leagueTitle)} logo"
                )
            }

        }

        if (fixtures.isExpanded) {
            Column {
                fixtures.fixtures.forEach { fixtureEntity ->
                    Fixture(
                        fixture = fixtureEntity,
                        onPredictionClick = { onPredictionClick(it) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "FixturesPerLeague", showBackground = true)
private fun PreviewFixturesPerLeague() {
    ChandChandTheme {
        var fixtures by remember { mutableStateOf(PreviewData.fixturesPerLeague) }
        FixturesPerLeague(fixtures, {
            val prevState = fixtures.isExpanded
            fixtures = fixtures.copy(isExpanded = !prevState)
        }, {})
    }
}

