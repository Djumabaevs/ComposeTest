package com.example.composetest

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.ceil

@Composable
fun AlertDialogScreen() {

    MyAlertDialog()

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MyAlertDialog() {

    val shouldShowDialog = remember {
        mutableStateOf(true)
    }

    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
                JetFundamentalsRouter.navigateTo(Screen.Navigation)
            },
            title = { Text(text = stringResource(id = R.string.alert_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.alert_dialog_text)) },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.colorPrimary)
                    ),
                    onClick = {
                        shouldShowDialog.value = false
                        JetFundamentalsRouter.navigateTo(Screen.Navigation)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.confirm),
                        color = Color.White
                    )

                }
            }
        )
    }
}









@Composable
fun ExploreButtonsScreen() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        MyButton()
        MyRadioGroup()
        MyFloatingActionButton()

        BackButtonHandler {
            JetFundamentalsRouter.navigateTo(Screen.Navigation)
        }
    }
}

@Composable
fun MyButton() {
    Button(onClick = {  },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.colorPrimary)),
        border = BorderStroke(
            1.dp,
            color = colorResource(id = R.color.colorPrimaryDark)
        )
    ) {
        Text(
            text = stringResource(id = R.string.button_text),
            color = Color.White)

    }
}

@Composable
fun MyRadioGroup() {

    val radioButtons = listOf(0,1,2)
    val selectedButton = remember {
        mutableStateOf(radioButtons.first())
    }

    Column() {
        radioButtons.forEach {index ->
            val isSelected = index == selectedButton.value
            val colors = RadioButtonDefaults.colors(
                selectedColor = colorResource(id = R.color.colorPrimary),
                unselectedColor = colorResource(id = R.color.colorPrimaryDark),
                disabledColor = Color.Gray
            )
            RadioButton(
                colors = colors,
                selected = isSelected,
                onClick = {
                    selectedButton.value = index
                }
            )
        }

    }
}

@Composable
fun MyFloatingActionButton() {

    FloatingActionButton(onClick = { },
        backgroundColor = colorResource(id = R.color.colorPrimary),
        contentColor = Color.White,
        content = {
            Icon(Icons.Filled.Favorite, contentDescription = "Test FAB" )
        }
    )
}




@Composable
fun NavigationScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NavigationButton(stringResource(id = R.string.text), Screen.Text)
            NavigationButton(stringResource(id = R.string.text_field), Screen.TextField)
            NavigationButton(stringResource(id = R.string.buttons), Screen.Buttons)
            NavigationButton(stringResource(id = R.string.progress_indicators), Screen.ProgressIndicator)
            NavigationButton(stringResource(id = R.string.alert_dialog), Screen.AlertDialog)
        }
    }
}

@Composable
fun NavigationButton(text: String, screen: Screen) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary)),
        onClick = { JetFundamentalsRouter.navigateTo(screen) }
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}




@Composable
fun ProgressIndicatorScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.colorPrimary),
            strokeWidth = 5.dp
        )
        LinearProgressIndicator(progress = 0.5f)
    }

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}




@Composable
fun TextFieldScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyTextField()
    }

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MyTextField() {
    val textValue = remember {
        mutableStateOf("")
    }
    val primaryColor = colorResource(id = R.color.colorPrimary)

    OutlinedTextField(label = {
        Text(text = stringResource(id = R.string.email))
    },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primaryColor,
            unfocusedLabelColor = primaryColor,
            cursorColor = primaryColor
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },)
}



@Composable
fun SurfaceScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize()) {
        MySurface(modifier = modifier.align(Alignment.Center))
    }

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MySurface(modifier: Modifier) {
    Surface(
        modifier = modifier.size(100.dp),
        color = Color.LightGray,
        contentColor = colorResource(id = com.raywenderlich.android.jetpackcompose.R.color.colorPrimary),
        elevation = 1.dp,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        MyColumn()

    }
}


@Composable
fun AlertDialogScreen() {

    MyAlertDialog()

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MyAlertDialog() {
    val shouldShowDialog = remember { mutableStateOf(true) }

    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
                JetFundamentalsRouter.navigateTo(Screen.Navigation)
            },
            title = { Text(text = stringResource(id = R.string.alert_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.alert_dialog_text)) },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary)),
                    onClick = {
                        shouldShowDialog.value = false
                        JetFundamentalsRouter.navigateTo(Screen.Navigation)
                    }) {
                    Text(
                        text = stringResource(id = R.string.confirm),
                        color = Color.White
                    )
                }
            }
        )
    }
}




@Composable
fun MyScaffold() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        contentColor = colorResource(id = R.color.colorPrimary),
        content = { MyRow() },
        topBar = { MyTopAppBar(scaffoldState = scaffoldState, scope = scope) },
        bottomBar = { MyBottomAppBar()},
        drawerContent = { MyColumn()}
    )
}



@Composable
fun MyTopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    val drawerState = scaffoldState.drawerState
    TopAppBar(
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        Icons.Default.Menu,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.menu)
                    )
                },
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed)
                            drawerState.open() else drawerState.close()
                    }
                }
            )
        },
        title = { Text(text = stringResource(R.string.app_name),
            color = Color.White)},
        backgroundColor = colorResource(id = R.color.colorPrimary)
    )
}

@Composable
fun MyBottomAppBar() {
    BottomAppBar(content = {},
        backgroundColor = colorResource(R.color.colorPrimary))
}



@Composable
fun MyScrollingScreen(modifier: Modifier = Modifier) {
    Column( modifier =
    modifier.verticalScroll(rememberScrollState())) {
        BookImage(R.drawable.advanced_architecture_android,
            R.string.advanced_architecture_android)
        BookImage(R.drawable.kotlin_aprentice,
            R.string.kotlin_apprentice)
        BookImage(R.drawable.kotlin_coroutines,
            R.string.kotlin_coroutines)
    }

}


@Composable
fun BookImage(@DrawableRes imageResId: Int, @StringRes contentDescriptionResId: Int) {
    Image(
        bitmap = ImageBitmap.imageResource(imageResId),
        contentDescription = stringResource(contentDescriptionResId),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.size(476.dp, 616.dp)
    )
}


@Composable
fun BookImage(imageResource: Int) {
    Image(
        modifier = Modifier.size(170.dp, 200.dp),
        painter = painterResource(id = imageResource),
        contentScale = ContentScale.Fit,
        contentDescription = stringResource(R.string.book_image)
    )
}

data class BookCategory(@StringRes val categoryResourceId: Int, val bookImageResources: List<Int>)




@Composable
fun ListItem(bookCategory: BookCategory, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = stringResource(bookCategory.categoryResourceId),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.colorPrimary)
        )
        Spacer(modifier = modifier.height(8.dp))

        LazyRow {
            items(bookCategory.bookImageResources) { items ->
                BookImage(items)
            }
        }
    }
}


@Composable
fun ListScreen() {
    MyList()

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MyList() {
    LazyColumn {
        items(items) { item -> ListItem(item)}
    }
}

fun RowScope.GridIcon(iconResource: IconResource) {

    val color = if(iconResource.isVisible)
        colorResource(R.color.colorPrimary)
    else Color.Transparent

    Icon(
        imageVector = iconResource.imageVector,
        tint = color,
        contentDescription = stringResource(R.string.grid_icon),
        modifier = Modifier
            .size(80.dp, 80.dp)
            .weight(1f)
    )
}

data class IconResource(val imageVector: ImageVector, val isVisible: Boolean)




@Composable
fun GridScreen() {
    GridView(columnCount = 3)

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun GridView(columnCount: Int) {
    val itemSize = items.size
    val rowCount = ceil(itemSize.toFloat() / columnCount).toInt()
    val gridItems = mutableListOf<List<IconResource>>()
    var position = 0

    for(i in 0 until rowCount) {
        val rowItem = mutableListOf<IconResource>()
        for (j in 0 until columnCount) {
            if(position.inc() <= itemSize) {
                rowItem.add(IconResource(items[position++], true))
            }
        }

        val itemsToFill = columnCount - rowItem.size
        for (j in 0 until itemsToFill) {
            rowItem.add(IconResource(Icons.Filled.Delete, false))
        }
        gridItems.add(rowItem)
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(gridItems) {items ->
            RowItem(items)
        }

    }
}

@Composable
fun RowItem(rowItems: List<IconResource>) {
    Row {
        for (element in rowItems)
            GridIcon(element)
    }
}







