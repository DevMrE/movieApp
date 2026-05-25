package com.kmp.movieapp.search.presentation

//@Composable
//fun SearchContent() {
//    val searchViewModel = koinViewModel<SearchViewModel>()
//    val results by searchViewModel.searchQueryState.collectAsStateWithLifecycle()
//    val focusManager = LocalFocusManager.current
//
//    val navigator: Navigator<Route> = koinInject()
//    val gridState = rememberLazyGridState()
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background),
//        state = gridState,
//        contentPadding = PaddingValues(all = MaterialTheme.padding.five),
//        verticalArrangement = Arrangement.spacedBy(
//            space = MaterialTheme.padding.defaultContentPadding,
//            alignment = Alignment.Top
//        ),
//        horizontalArrangement = Arrangement.spacedBy(
//            space = MaterialTheme.padding.defaultContentPadding,
//            alignment = Alignment.CenterHorizontally
//        ),
//    ) {
//        items(items = results.searchResults, key = { it.hashCode() }) { movie ->
//            MediaCard(
//                title = "",
//                posterPath = movie.posterPath,
//                enableGradient = false
//            ) {
//                navigator.navigateTo(
//                    route = HomeNavigation.ContentDetailRoute(
//                        id = movie.id,
//                        mediaCategory = movie.mediaCategory
//                    )
//                )
//                focusManager.clearFocus(force = true)
//            }
//        }
//    }
//}