package ml.zedlabs.tvtracker.ui.list

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.domain.usecases.GetUserAddedListUseCase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    val getUserAddedListUseCase: GetUserAddedListUseCase
) : ViewModel() {

    val userAddedListState = getUserAddedListUseCase.getUserAddedList()

    fun addToUserAddedList(item: AddedList) {
        viewModelScope.launch {
            getUserAddedListUseCase.addToUserAddedList(item)
        }
    }

    fun deleteAllEntries() {
        viewModelScope.launch {
            getUserAddedListUseCase.deleteAllItems()
        }
    }
}