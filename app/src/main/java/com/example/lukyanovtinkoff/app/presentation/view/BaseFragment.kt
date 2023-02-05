package com.example.lukyanovtinkoff.app.presentation.view

import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.lukyanovtinkoff.app.presentation.utils.RequestState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel : androidx.lifecycle.ViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected lateinit var viewModel: ViewModel

    protected var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!

    /**
     * Collect flow safely with [repeatOnLifecycle] API
     */
    protected fun collectFlowSafely(
        lifecycleState: Lifecycle.State,
        collect: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                collect()
            }
        }
    }

    /**
     * Collect [RequestState] with [collectFlowSafely] and optional states params
     * @param state for working with all states
     * @param onError for error handling
     * @param onSuccess for working with data
     */
    protected fun <T> StateFlow<RequestState<T>>.collectRequestState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        state: ((RequestState<T>) -> Unit)? = null,
        onError: ((error: String) -> Unit),
        onSuccess: ((data: T) -> Unit)
    ) {
        collectFlowSafely(lifecycleState) {
            this.collect {
                state?.invoke(it)
                when (it) {
                    is RequestState.Idle -> {}
                    is RequestState.Loading -> {}
                    is RequestState.Error -> onError.invoke(it.error)
                    is RequestState.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }

    /**
     * Setup views visibility depending on [UIState] states.
     * @param isNavigateWhenSuccess is responsible for displaying views depending on whether
     * to navigate further or stay this Fragment
     */
    protected fun <T> RequestState<T>.setupViewVisibility(
        content: View, loader: View, error: View? = null
    ) {
        fun showLoading() {
            content.isVisible = false
            loader.isVisible = true
            error?.isVisible = false
        }

        fun showError() {
            content.isVisible = false
            loader.isVisible = false
            error?.isVisible = true
        }

        fun showContent() {
            content.isVisible = true
            loader.isVisible = false
            error?.isVisible = false
        }

        when (this) {
            is RequestState.Idle -> {}
            is RequestState.Loading -> showLoading()
            is RequestState.Error -> showError()
            is RequestState.Success -> showContent()
        }
    }
}