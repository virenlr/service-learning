package in.christuniversity.servicelearning;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import static in.christuniversity.servicelearning.MainActivity.MODE;
import static in.christuniversity.servicelearning.MainActivity.mBackCount;
import static in.christuniversity.servicelearning.SplashActivity.LANGUAGE_SELECTED;
import static in.christuniversity.servicelearning.SplashActivity.PREFERENCE_FILE;

public class ChapterFragment extends Fragment {

    private int mMode;

    private FirebaseFirestore firestoreDB;
    private ArrayList<ChapterPart> chapterPartArrayList;

    private SimpleExoPlayer mExoPlayer;
    private PlayerView mExoPlayerView;
    private FrameLayout mVideoFrame;

    private boolean mExoPlayerFullscreen = false;
    private ImageView mFullScreenIcon;
    private Dialog mFullScreenDialog;

    private int mResumeWindow;
    private long positionInVideo;
    private boolean readyToPlay = true;

    private ViewGroup.LayoutParams mLayoutParams;
    private static final String PART_NUMBER = "part_number";
    private static final String TOTAL_NUMBER_OF_PARTS = "total_number_of_parts";
    private static final String CHAPTER_PARTS_ARRAY_LIST = "chapter_parts_array_list";
    private static final String POSITION_IN_VIDEO_KEY = "position_in_video";
    private static final String READY_TO_PLAY = "ready_to_play";
    private static final String STATE_PLAYER_FULLSCREEN = "playerFullscreen";

    private int currentPart;
    private int totalNumberOfParts;
    private String videoUrl = null;
    private TextView mChapterNotesHeaderTextView;
    private TextView mChapterNotesTextView;
    private TextView mAdditionalReadingHeaderTextView;
    private TextView mAdditionalReadingTextView;
    private MaterialButton mPreviousButton;
    private MaterialButton mNextButton;
    private TextView mPartNumberTextView;

    private SwipeRefreshLayout mSwipeToRefresh;

    public ChapterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBackCount = 0;
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = view.findViewById(R.id.chapter_toolbar);
        Bundle args = getArguments();

        if (args != null && args.containsKey(MODE)) {
            mMode = args.getInt(MODE);
        }

        mVideoFrame = view.findViewById(R.id.main_media_frame);
        mExoPlayerView = view.findViewById(R.id.chapter_media);
        mLayoutParams = mExoPlayerView.getLayoutParams();
        firestoreDB = FirebaseFirestore.getInstance();
        mChapterNotesHeaderTextView = view.findViewById(R.id.chapter_notes_header_text_view);
        mAdditionalReadingHeaderTextView = view.findViewById(R.id.additional_reading_header_text_view);
        mChapterNotesTextView = view.findViewById(R.id.chapter_notes_text_view);
        mAdditionalReadingTextView = view.findViewById(R.id.additional_reading_text_view);
        mPreviousButton = view.findViewById(R.id.previous_button);
        mNextButton = view.findViewById(R.id.next_button);
        mPartNumberTextView = view.findViewById(R.id.part_number_text_view);

        mSwipeToRefresh = view.findViewById(R.id.swipe_to_refresh);
        mSwipeToRefresh.setColorSchemeColors(Objects.requireNonNull(getContext()).getResources().getColor(R.color.colorAccent));

        switch (mMode) {
            case 0:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.home));
                break;
            case 1:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_1));
                break;
            case 2:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_2));
                break;
            case 3:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_3));
                break;
            case 4:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_4));
                break;
            case 5:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_5));
                break;
            case 6:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_6));
                break;
            case 7:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_7));
                break;
            case 8:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_8));
                break;
            case 9:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_9));
                break;
            case 10:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_10));
                break;
            case 11:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_11));
                break;
            case 12:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_12));
                break;
            case 13:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_13));
                break;
            case 14:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_14));
                break;
            case 15:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_15));
                break;
            case 16:
                ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.chapter_16));
                break;
        }

        SharedPreferences sharedpreferences = Objects.requireNonNull(getContext()).getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        final int languageSelected = sharedpreferences.getInt(LANGUAGE_SELECTED, 1);

        if (savedInstanceState != null) {
            positionInVideo = savedInstanceState.getLong(POSITION_IN_VIDEO_KEY, 0);
            readyToPlay = savedInstanceState.getBoolean(READY_TO_PLAY);
            mExoPlayerFullscreen = savedInstanceState.getBoolean(STATE_PLAYER_FULLSCREEN);
            currentPart = savedInstanceState.getInt(PART_NUMBER);
            totalNumberOfParts = savedInstanceState.getInt(TOTAL_NUMBER_OF_PARTS);
            chapterPartArrayList = savedInstanceState.getParcelableArrayList(CHAPTER_PARTS_ARRAY_LIST);

            updatePartNumberTextView();

            if (chapterPartArrayList != null && chapterPartArrayList.size() > 0) {

                videoUrl = Objects.requireNonNull(chapterPartArrayList).get(currentPart - 1).url;

                setTextViewsAndRemoveIfUnnecessary();

                initializePlayer();

                if (totalNumberOfParts > 1) {
                    if (currentPart == 1) {
                        mPreviousButton.setEnabled(false);
                    } else {
                        mPreviousButton.setEnabled(true);
                    }

                    if (currentPart == totalNumberOfParts) {
                        mNextButton.setEnabled(false);
                    } else {
                        mNextButton.setEnabled(true);
                    }

                    initializeButtons();
                } else {
                    mPreviousButton.setVisibility(View.GONE);
                    mNextButton.setVisibility(View.GONE);
                }
            } else {
                fetchChapterParts(languageSelected);
            }

        } else {
            fetchChapterParts(languageSelected);
        }


        mSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchChapterParts(languageSelected);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {

        if (mExoPlayer != null) {
            if (readyToPlay) {
                mExoPlayer.setPlayWhenReady(true);
            } else {
                mExoPlayer.setPlayWhenReady(false);
            }

            if (mExoPlayerFullscreen) {
                openFullscreenDialog();
            }
        }

        super.onResume();
    }

    @Override
    public void onPause() {
        if (mExoPlayer != null) {
            mResumeWindow = mExoPlayerView.getPlayer().getCurrentWindowIndex();
            positionInVideo = mExoPlayer.getCurrentPosition();
            readyToPlay = mExoPlayer.getPlayWhenReady();
            mExoPlayer.setPlayWhenReady(false);
        }

        if (mExoPlayerFullscreen) {
            closeFullscreenDialog();
            mExoPlayerFullscreen = true;
        }

        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putInt(PART_NUMBER, currentPart);
        currentState.putInt(TOTAL_NUMBER_OF_PARTS, totalNumberOfParts);
        currentState.putParcelableArrayList(CHAPTER_PARTS_ARRAY_LIST, chapterPartArrayList);
        currentState.putLong(POSITION_IN_VIDEO_KEY, positionInVideo);
        currentState.putBoolean(READY_TO_PLAY, readyToPlay);
        currentState.putBoolean(STATE_PLAYER_FULLSCREEN, mExoPlayerFullscreen);
    }

    private void fetchChapterParts(int languageNumber) {
        mSwipeToRefresh.setRefreshing(true);

        String fullQuery = "";

        if (languageNumber == 3) {
            String hindiSegment = "hindi/0/";
            fullQuery = hindiSegment + "chapter" + mMode;
        } else if (languageNumber == 2) {
            String tamilSegment = "tamil/0/";
            fullQuery = tamilSegment + "chapter" + mMode;
        } else {
            String kannadaSegment = "kannada/0/";
            fullQuery = kannadaSegment + "chapter" + mMode;
        }

        firestoreDB.collection(fullQuery)
                .orderBy("part", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        Activity activity = getActivity();
                        if (isAdded() && activity != null) {
                            chapterPartArrayList = new ArrayList<>();

                            for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                                ChapterPart chapterPart = doc.toObject(ChapterPart.class);
                                chapterPartArrayList.add(chapterPart);
                            }

                            if (chapterPartArrayList.size() > 0) {
                                currentPart = 1;
                                totalNumberOfParts = chapterPartArrayList.size();

                                updatePartNumberTextView();

                                videoUrl = chapterPartArrayList.get(0).url;

                                setTextViewsAndRemoveIfUnnecessary();

                                initializePlayer();

                                if (totalNumberOfParts > 1) {
                                    if (currentPart == 1) {
                                        mPreviousButton.setEnabled(false);
                                    } else {
                                        mPreviousButton.setEnabled(true);
                                    }

                                    if (currentPart == totalNumberOfParts) {
                                        mNextButton.setEnabled(false);
                                    } else {
                                        mNextButton.setEnabled(true);
                                    }

                                    initializeButtons();
                                } else {
                                    mPreviousButton.setVisibility(View.GONE);
                                    mNextButton.setVisibility(View.GONE);
                                }
                            } else {
                                Toast.makeText(getContext(), getString(R.string.no_videos_available), Toast.LENGTH_LONG).show();
                            }
                            mSwipeToRefresh.setRefreshing(false);
                        }
                    }
                });
    }

    private void initializePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(Objects.requireNonNull(getContext()));
        mExoPlayerView.setPlayer(mExoPlayer);

        initFullscreenDialog();
        initFullscreenButton();

        boolean haveResumePosition = mResumeWindow != C.INDEX_UNSET;

        // This is the MediaSource representing the media to be played.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), getString(R.string.app_name)));

        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videoUrl));
        // Prepare the player with the source.

        mExoPlayer.prepare(videoSource);

        if (haveResumePosition) {
            mExoPlayer.seekTo(mResumeWindow, positionInVideo);
        }
        mExoPlayer.setPlayWhenReady(readyToPlay);
    }

    private void initFullscreenButton() {
        mFullScreenIcon = mExoPlayerView.findViewById(R.id.exo_fullscreen_icon);
        FrameLayout mFullScreenButton = mExoPlayerView.findViewById(R.id.exo_fullscreen_button);
        mFullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mExoPlayerFullscreen)
                    openFullscreenDialog();
                else
                    closeFullscreenDialog();
            }
        });
    }

    private void initFullscreenDialog() {
        mFullScreenDialog = new Dialog(Objects.requireNonNull(getContext()), android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            public void onBackPressed() {
                if (mExoPlayerFullscreen)
                    closeFullscreenDialog();
                super.onBackPressed();
            }
        };
    }

    private void openFullscreenDialog() {
        mVideoFrame.removeView(mExoPlayerView);
        mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.ic_fullscreen_skrink));
        mExoPlayerFullscreen = true;
        mFullScreenDialog.show();
    }

    private void closeFullscreenDialog() {
        mExoPlayerView.setLayoutParams(mLayoutParams);
        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
        mVideoFrame.addView(mExoPlayerView);
        mExoPlayerFullscreen = false;
        mFullScreenDialog.dismiss();
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.ic_fullscreen_expand));
    }

    private void initializeButtons() {
        mPreviousButton.setVisibility(View.VISIBLE);
        mNextButton.setVisibility(View.VISIBLE);

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPart--;
                updatePartNumberTextView();
                videoUrl = chapterPartArrayList.get(currentPart - 1).url;
                setTextViewsAndRemoveIfUnnecessary();
                positionInVideo = 0;
                initializePlayer();

                if (currentPart == 1) {
                    mPreviousButton.setEnabled(false);
                } else {
                    mPreviousButton.setEnabled(true);
                }

                if (currentPart == totalNumberOfParts) {
                    mNextButton.setEnabled(false);
                } else {
                    mNextButton.setEnabled(true);
                }
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPart++;
                updatePartNumberTextView();
                videoUrl = chapterPartArrayList.get(currentPart - 1).url;
                setTextViewsAndRemoveIfUnnecessary();
                positionInVideo = 0;
                initializePlayer();

                if (currentPart == 1) {
                    mPreviousButton.setEnabled(false);
                } else {
                    mPreviousButton.setEnabled(true);
                }

                if (currentPart == totalNumberOfParts) {
                    mNextButton.setEnabled(false);
                } else {
                    mNextButton.setEnabled(true);
                }
            }
        });
    }

    private void setTextViewsAndRemoveIfUnnecessary() {
        String chapterNotes = chapterPartArrayList.get(currentPart - 1).notes;
        String additionalReading = chapterPartArrayList.get(currentPart - 1).extra;

        if (chapterNotes.length() > 0) {
            mChapterNotesHeaderTextView.setVisibility(View.VISIBLE);
            mChapterNotesTextView.setVisibility(View.VISIBLE);

            mChapterNotesTextView.setText(chapterNotes);
        } else {
            mChapterNotesHeaderTextView.setVisibility(View.GONE);
            mChapterNotesTextView.setVisibility(View.GONE);
        }

        if (additionalReading.length() > 0) {
            mAdditionalReadingHeaderTextView.setVisibility(View.VISIBLE);
            mAdditionalReadingTextView.setVisibility(View.VISIBLE);

            mAdditionalReadingTextView.setText(additionalReading);
        } else {
            mAdditionalReadingHeaderTextView.setVisibility(View.GONE);
            mAdditionalReadingTextView.setVisibility(View.GONE);
        }
    }

    private void updatePartNumberTextView() {
        mPartNumberTextView.setText(getString(R.string.part_number, currentPart, totalNumberOfParts));
    }
}