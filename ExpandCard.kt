

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Expand Card with cool animation
 * */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandCard(
    title: String,
    content: String
) {

    var isExpand by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (isExpand) 180f else 0f)
    Card(shape = Shapes.medium, modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ), onClick = {
        isExpand = !isExpand
    }) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    title,
                    modifier = Modifier.weight(6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { isExpand = !isExpand }, modifier = Modifier
                        .weight(1f)
                        .rotate(rotateState)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Icon"
                    )
                }
            }
            if (isExpand)
                Text(
                    text = content, maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
        }
    }
}

@Composable
@Preview
fun ExpandCardPreview() {
    ExpandCard(
        title = "Title",
        content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    )
}