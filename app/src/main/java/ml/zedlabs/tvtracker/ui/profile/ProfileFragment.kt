package ml.zedlabs.tvtracker.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.tvtracker.base.BaseAndroidFragment
import ml.zedlabs.tvtracker.ui.theme.TvTrackerTheme
import ml.zedlabs.tvtracker.util.Constants.IS_DARK_THEME_ENABLED

@AndroidEntryPoint
class ProfileFragment : BaseAndroidFragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TvTrackerTheme() {
                    TabLayoutTest()
                }
            }
        }
    }

    @Composable
    fun ProfileScreenParentLayout() {

        val themeIconToggleState = remember { mutableStateOf(false) }

        Row {
            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = "Enable Dark Mode"
            )
            Switch(
                checked = themeIconToggleState.value,
                onCheckedChange = {
                    themeIconToggleState.value = !themeIconToggleState.value
                    writeBooleanToSharedPreferenceFile(
                        IS_DARK_THEME_ENABLED,
                        themeIconToggleState.value
                    )
                }
            )
        }
    }

    enum class Tabs(val position: Int){
        TAB1(0),
        TAB2(1),
        TAB3(2),
        TAB4(3),
    }
    @Composable
    fun TabLayoutTest() {
        var tabIndex by remember {
            mutableStateOf(Tabs.TAB1)
        }
        val tabTitles = listOf( "TAB1",
            "TAB2",
            "TAB3",
            "TAB4",
        )

        TabRow(
            selectedTabIndex = tabIndex.position
        ) {
            tabTitles.forEachIndexed { index: Int, s: String ->
                Tab(selected = tabIndex.position == index,
                    onClick = {
                    Toast.makeText(context, "tab click! $index", Toast.LENGTH_SHORT).show()
                },
                    text = {
                        Text(text = "Title")
                    }
                )
            }
        }

        // can have different composables here based on the selected selected tab index
    }
}
























