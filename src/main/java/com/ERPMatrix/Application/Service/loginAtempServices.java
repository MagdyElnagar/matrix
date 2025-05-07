package com.ERPMatrix.Application.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class loginAtempServices {

	private static final int ATTEMP_INCREMENT = 1;
	private static final int MAXIMUM_NUMBER_OF_ATEEMPTS = 5;
	private LoadingCache<String, Integer> loginAttemptCache;

	public loginAtempServices() {
		super();
		loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES).maximumSize(100)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public void addUserToLoginAttemptCache(String username) {
		int attempts = 0;
		try {
			attempts = ATTEMP_INCREMENT + loginAttemptCache.get(username);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginAttemptCache.put(username, attempts);

	}

	public void evictUserFromLoginAttemptCache(String username) {

		loginAttemptCache.invalidate(username);
	}

	public boolean hasExceededMaxAttempts(String username) {

		try {
			return loginAttemptCache.get(username) >= MAXIMUM_NUMBER_OF_ATEEMPTS;

		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
