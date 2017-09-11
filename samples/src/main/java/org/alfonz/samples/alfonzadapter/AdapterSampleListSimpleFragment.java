package org.alfonz.samples.alfonzadapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import org.alfonz.samples.R;
import org.alfonz.samples.alfonzmvvm.BaseFragment;
import org.alfonz.samples.databinding.FragmentAdapterSampleListBinding;


public class AdapterSampleListSimpleFragment extends BaseFragment<AdapterSampleView, AdapterSampleViewModel, FragmentAdapterSampleListBinding> implements AdapterSampleView
{
	private MessageListSimpleAdapter mAdapter;


	public static AdapterSampleListSimpleFragment newInstance()
	{
		return new AdapterSampleListSimpleFragment();
	}


	@Nullable
	@Override
	public Class<AdapterSampleViewModel> getViewModelClass()
	{
		return AdapterSampleViewModel.class;
	}


	@Override
	public FragmentAdapterSampleListBinding inflateBindingLayout(@NonNull LayoutInflater inflater)
	{
		return FragmentAdapterSampleListBinding.inflate(inflater);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		getBinding().executePendingBindings(); // set layout manager in recycler via binding adapter
		setupAdapter();
	}


	@Override
	public void onItemClick(String message)
	{
		String newMessage = getViewModel().addMessage();
		showSnackbar(getString(R.string.fragment_adapter_sample_hello, newMessage));
	}


	@Override
	public boolean onItemLongClick(String message)
	{
		getViewModel().removeMessage(message);
		showToast(getString(R.string.fragment_adapter_sample_goodbye, message));
		return true;
	}


	private void setupAdapter()
	{
		if(mAdapter == null)
		{
			mAdapter = new MessageListSimpleAdapter(this, getViewModel());
			getBinding().fragmentAdapterSampleListRecycler.setAdapter(mAdapter);
		}
	}
}
