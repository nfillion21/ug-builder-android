package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import pgm.poolp.ugbuilder.model.Player
import pgm.poolp.ugbuilder.model.tmnt_players
import pgm.poolp.ugbuilder.ui.common.CourseListItem
import pgm.poolp.ugbuilder.ui.theme.BlueTheme

@Composable
fun MyCourses(
    cours: List<Player>,
    selectCourse: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight())
        }
        item {
            CoursesAppBar()
        }
        itemsIndexed(cours) { index, course ->
            MyCourse(course, index, selectCourse)
        }
    }
}

@Composable
fun MyCourse(
    player: Player,
    index: Int,
    selectCourse: (Long) -> Unit
) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        val stagger = if (index % 2 == 0) 72.dp else 16.dp
        Spacer(modifier = Modifier.width(stagger))
        CourseListItem(
            player = player,
            onClick = { selectCourse(player.id) },
            shape = RoundedCornerShape(topStart = 24.dp),
            modifier = Modifier.height(96.dp)
        )
    }
}

@Preview(name = "My Courses")
@Composable
private fun MyCoursesPreview() {
    BlueTheme {
        MyCourses(
            cours = tmnt_players,
            selectCourse = { }
        )
    }
}