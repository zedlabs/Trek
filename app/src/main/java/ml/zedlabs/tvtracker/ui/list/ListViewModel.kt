package ml.zedlabs.tvtracker.ui.list

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

    private val _userAddedListState =
        MutableStateFlow<List<AddedList>>(emptyList())
    val userAddedListState = _userAddedListState.asStateFlow()

    init {
        getUserAddedList()
    }

    fun getUserAddedList() {
        viewModelScope.launch {
            _userAddedListState.value =
                getUserAddedListUseCase.getUserAddedList()
        }
    }
}