package com.michalplachta.galactic.java.internal;

import com.michalplachta.galactic.java.service.FollowersService.Version4.*;
import io.vavr.API;
import io.vavr.Tuple;
import io.vavr.Tuple1;

// NOTE: Some patterns can also be autogenerated using javaslang-match
public class RemoteFollowersDataPatterns {
    public static API.Match.Pattern0<? extends RemoteFollowersData> NotRequestedYet() {
        return API.Match.Pattern0.of(NotRequestedYet.class);
    }

    public static API.Match.Pattern0<? extends RemoteFollowersData> Loading() {
        return API.Match.Pattern0.of(Loading.class);
    }

    public static Tuple1<Integer> Fetched(Fetched fetched) {
        return Tuple.of(fetched.followers);
    }

    public static API.Match.Pattern1<? extends RemoteFollowersData, Integer> Fetched(API.Match.Pattern<Integer, ?> p1) {
        return API.Match.Pattern1.of(Fetched.class, p1, RemoteFollowersDataPatterns::Fetched);
    }

    public static Tuple1<String> Failed(Failed failed) {
        return Tuple.of(failed.errorMessage);
    }

    public static API.Match.Pattern1<? extends RemoteFollowersData, String> Failed(API.Match.Pattern<String, ?> p1) {
        return API.Match.Pattern1.of(Failed.class, p1, RemoteFollowersDataPatterns::Failed);
    }
}
