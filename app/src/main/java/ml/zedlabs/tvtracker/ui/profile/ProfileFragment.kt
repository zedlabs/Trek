package ml.zedlabs.tvtracker.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconToggleButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                    ProfileScreenParentLayout()
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
}