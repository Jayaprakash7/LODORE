package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import lodore.com.lodore.Pojo.PerfumeFilterRequest;
import lodore.com.lodore.Pojo.PerfumeRequest;
import lodore.com.lodore.Pojo.PerfumeResponse;
import lodore.com.lodore.R;

import lodore.com.lodore.adapter.PerfumeFragmentAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfumeFragment extends Fragment {


    private RecyclerView recyclerViewPerfume, recyclerOrderByPrice, recyclerFilter;
    private PerfumeFragmentAdapter adapter;
    Button btnPriceLowtoHigh, btnFilter, btnCheck;
    ProgressDialog progressDialog;
    CheckBox check_cool, check_warm, check_morning, check_daily, check_night, check_amber, check_oud, check_wood, check_fruit,
            check_powder, check_spices, check_leather, check_musk, check_sweet, check_fragrant_plant, check_flower, check_select_all,
            check_occasion_select_all, check_select_all_family;

    String cool = "", warm = "", morning = "", daily = "", night = "", amber = "", oud = "", wood = "", fruit = "",
            powder = "", spices = "", leather = "", musk = "", sweet = "", fragrant_plant = "", flower = "";


    public PerfumeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfume, container, false);
        progressDialog = new ProgressDialog(getContext());

        recyclerViewPerfume = (RecyclerView) view.findViewById(R.id.perfume_recycler);
        recyclerOrderByPrice = (RecyclerView) view.findViewById(R.id.perfume_order_by_price_recycler);
        recyclerFilter = (RecyclerView) view.findViewById(R.id.perfume_filter_recycler);
        btnFilter = (Button) view.findViewById(R.id.btn_filter);
        btnCheck = (Button) view.findViewById(R.id.update_search);
        btnPriceLowtoHigh = (Button) view.findViewById(R.id.price_low_to_high);

        check_cool = (CheckBox) view.findViewById(R.id.check_cool);
        check_warm = (CheckBox) view.findViewById(R.id.check_warm);
        check_morning = (CheckBox) view.findViewById(R.id.check_morning);
        check_daily = (CheckBox) view.findViewById(R.id.check_daily);
        check_night = (CheckBox) view.findViewById(R.id.check_night);
        check_amber = (CheckBox) view.findViewById(R.id.check_amber);
        check_oud = (CheckBox) view.findViewById(R.id.check_oud);
        check_wood = (CheckBox) view.findViewById(R.id.check_wood);
        check_fruit = (CheckBox) view.findViewById(R.id.check_fruit);
        check_powder = (CheckBox) view.findViewById(R.id.check_powder);
        check_spices = (CheckBox) view.findViewById(R.id.check_spices);
        check_leather = (CheckBox) view.findViewById(R.id.check_leather);
        check_musk = (CheckBox) view.findViewById(R.id.check_musk);
        check_sweet = (CheckBox) view.findViewById(R.id.check_sweet);
        check_fragrant_plant = (CheckBox) view.findViewById(R.id.check_fragrant_plant);
        check_flower = (CheckBox) view.findViewById(R.id.check_flowers);
        check_select_all = (CheckBox) view.findViewById(R.id.check_selectall);
        check_occasion_select_all = (CheckBox) view.findViewById(R.id.check_occasion_select_all);
        check_select_all_family = (CheckBox) view.findViewById(R.id.check_select_all_family);


        check_cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check_cool.isChecked()) {
                    cool = "cool";
                    Log.d("Cool Checked", "onClick: " + cool);
                } else {
                    cool = "";
                    Log.d("Cool Checked", "onClick: " + cool);
                }
                if (check_cool.isChecked() && check_warm.isChecked()) {
                    check_select_all.setChecked(true);
                } else if (!check_cool.isChecked() || !check_warm.isChecked()) {
                    check_select_all.setChecked(false);
                }
            }
        });
        check_warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_warm.isChecked()) {
                    warm = "warm";
                    Log.d("Cool Checked", "onClick: " + warm);
                } else {
                    warm = "";
                    Log.d("Cool Checked", "onClick: " + warm);
                }

                if (check_cool.isChecked() && check_warm.isChecked()) {
                    check_select_all.setChecked(true);
                } else if (!check_cool.isChecked() || !check_warm.isChecked()) {
                    check_select_all.setChecked(false);
                }
            }
        });
        check_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_morning.isChecked()) {
                    morning = "morning";
                    Log.d("Cool Checked", "onClick: " + morning);
                } else {
                    morning = "";
                    Log.d("Cool Checked", "onClick: " + morning);
                }

                if (check_morning.isChecked() && check_daily.isChecked() && check_night.isChecked()) {
                    check_occasion_select_all.setChecked(true);
                } else if (!check_morning.isChecked() || !check_daily.isChecked() || !check_night.isChecked()) {
                    check_occasion_select_all.setChecked(false);
                }
            }
        });
        check_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_daily.isChecked()) {
                    daily = "daily";
                    Log.d("Cool Checked", "onClick: " + daily);
                } else {
                    daily = "";
                    Log.d("Cool Checked", "onClick: " + daily);
                }
                if (check_morning.isChecked() && check_daily.isChecked() && check_night.isChecked()) {
                    check_occasion_select_all.setChecked(true);
                } else if (!check_morning.isChecked() || !check_daily.isChecked() || !check_night.isChecked()) {
                    check_occasion_select_all.setChecked(false);
                }
            }
        });
        check_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_night.isChecked()) {
                    night = "night";
                    Log.d("Cool Checked", "onClick: " + night);
                } else {
                    night = "";
                    Log.d("Cool Checked", "onClick: " + night);
                }
                if (check_morning.isChecked() && check_daily.isChecked() && check_night.isChecked()) {
                    check_occasion_select_all.setChecked(true);
                } else if (!check_morning.isChecked() || !check_daily.isChecked() || !check_night.isChecked()) {
                    check_occasion_select_all.setChecked(false);
                }
            }
        });
        check_amber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_amber.isChecked()) {
                    amber = "amber";
                    Log.d("Cool Checked", "onClick: " + amber);
                } else {
                    amber = "";
                    Log.d("Cool Checked", "onClick: " + amber);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });
        check_oud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_oud.isChecked()) {
                    oud = "oud";
                    Log.d("Cool Checked", "onClick: " + oud);
                } else {
                    oud = "";
                    Log.d("Cool Checked", "onClick: " + oud);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_wood.isChecked()) {
                    wood = "wood";
                    Log.d("Cool Checked", "onClick: " + wood);
                } else {
                    wood = "";
                    Log.d("Cool Checked", "onClick: " + wood);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_fruit.isChecked()) {
                    fruit = "fruit";
                    Log.d("Cool Checked", "onClick: " + fruit);
                } else {
                    fruit = "";
                    Log.d("Cool Checked", "onClick: " + fruit);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_powder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_powder.isChecked()) {
                    powder = "powder";
                    Log.d("Cool Checked", "onClick: " + powder);
                } else {
                    powder = "";
                    Log.d("Cool Checked", "onClick: " + powder);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_spices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_spices.isChecked()) {
                    spices = "spices";
                    Log.d("Cool Checked", "onClick: " + spices);
                } else {
                    spices = "";
                    Log.d("Cool Checked", "onClick: " + spices);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_leather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_leather.isChecked()) {
                    leather = "leather";
                    Log.d("Cool Checked", "onClick: " + leather);
                } else {
                    leather = "";
                    Log.d("Cool Checked", "onClick: " + leather);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_musk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_musk.isChecked()) {
                    musk = "musk";
                    Log.d("Cool Checked", "onClick: " + musk);
                } else {
                    musk = "";
                    Log.d("Cool Checked", "onClick: " + musk);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_sweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_sweet.isChecked()) {
                    sweet = "sweet";
                    Log.d("Cool Checked", "onClick: " + sweet);
                } else {
                    sweet = "";
                    Log.d("Cool Checked", "onClick: " + sweet);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_fragrant_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_fragrant_plant.isChecked()) {
                    fragrant_plant = "fragrant plant";
                    Log.d("Cool Checked", "onClick: " + fragrant_plant);
                } else {
                    fragrant_plant = "";
                    Log.d("Cool Checked", "onClick: " + fragrant_plant);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });
        check_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_flower.isChecked()) {
                    flower = "flower";
                    Log.d("Cool Checked", "onClick: " + flower);
                } else {
                    flower = "";
                    Log.d("Cool Checked", "onClick: " + flower);
                }
                if (check_amber.isChecked() && check_oud.isChecked() && check_wood.isChecked()
                        && check_fruit.isChecked() && check_powder.isChecked() && check_spices.isChecked()
                        && check_leather.isChecked() && check_musk.isChecked() && check_sweet.isChecked()
                        && check_fragrant_plant.isChecked() && check_flower.isChecked()) {
                    check_select_all_family.setChecked(true);
                } else if (!check_amber.isChecked() || !check_oud.isChecked() || !check_wood.isChecked()
                        || !check_fruit.isChecked() || !check_powder.isChecked() || !check_spices.isChecked()
                        || !check_leather.isChecked() || !check_musk.isChecked() || !check_sweet.isChecked()
                        || !check_fragrant_plant.isChecked() || !check_flower.isChecked()) {
                    check_select_all_family.setChecked(false);
                }
            }
        });

        check_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_select_all.isChecked()) {
                    check_cool.setChecked(true);
                    check_warm.setChecked(true);
                    cool = "cool";
                    warm = "warm";
                    Log.d("Cool Checked", "onClick: " + cool);
                    Log.d("Cool Checked", "onClick: " + warm);
                } else {
                    check_cool.setChecked(false);
                    check_warm.setChecked(false);
                    cool = "";
                    warm = "";
                    Log.d("Cool Checked", "onClick: " + cool);
                    Log.d("Cool Checked", "onClick: " + warm);
                }

            }
        });

        check_occasion_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_occasion_select_all.isChecked()) {
                    check_morning.setChecked(true);
                    check_daily.setChecked(true);
                    check_night.setChecked(true);
                    morning = "morning";
                    daily = "daily";
                    night = "night";
                    Log.d("Cool Checked", "onClick: " + morning);
                    Log.d("Cool Checked", "onClick: " + daily);
                    Log.d("Cool Checked", "onClick: " + night);
                } else {
                    check_morning.setChecked(false);
                    check_daily.setChecked(false);
                    check_night.setChecked(false);
                    morning = "";
                    daily = "";
                    night = "";
                    Log.d("Cool Checked", "onClick: " + morning);
                    Log.d("Cool Checked", "onClick: " + daily);
                    Log.d("Cool Checked", "onClick: " + night);
                }
            }
        });

        check_select_all_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_select_all_family.isChecked()) {
                    check_amber.setChecked(true);
                    check_oud.setChecked(true);
                    check_wood.setChecked(true);
                    check_fruit.setChecked(true);
                    check_powder.setChecked(true);
                    check_spices.setChecked(true);
                    check_leather.setChecked(true);
                    check_musk.setChecked(true);
                    check_sweet.setChecked(true);
                    check_fragrant_plant.setChecked(true);
                    check_flower.setChecked(true);

                    amber = "amber";
                    oud = "oud";
                    wood = "wood";
                    fruit = "fruit";
                    powder = "powder";
                    spices = "spices";
                    leather = "leather";
                    musk = "musk";
                    sweet = "sweet";
                    fragrant_plant = "fragrant plant";
                    flower = "flower";

                    Log.d("Cool Checked", "onClick: " + amber);
                    Log.d("Cool Checked", "onClick: " + oud);
                    Log.d("Cool Checked", "onClick: " + wood);
                    Log.d("Cool Checked", "onClick: " + fruit);
                    Log.d("Cool Checked", "onClick: " + powder);
                    Log.d("Cool Checked", "onClick: " + spices);
                    Log.d("Cool Checked", "onClick: " + leather);
                    Log.d("Cool Checked", "onClick: " + musk);
                    Log.d("Cool Checked", "onClick: " + sweet);
                    Log.d("Cool Checked", "onClick: " + fragrant_plant);
                    Log.d("Cool Checked", "onClick: " + flower);
                } else {
                    check_amber.setChecked(false);
                    check_oud.setChecked(false);
                    check_wood.setChecked(false);
                    check_fruit.setChecked(false);
                    check_powder.setChecked(false);
                    check_spices.setChecked(false);
                    check_leather.setChecked(false);
                    check_musk.setChecked(false);
                    check_sweet.setChecked(false);
                    check_fragrant_plant.setChecked(false);
                    check_flower.setChecked(false);

                    amber = "";
                    oud = "";
                    wood = "";
                    fruit = "";
                    powder = "";
                    spices = "";
                    leather = "";
                    musk = "";
                    sweet = "";
                    fragrant_plant = "";
                    flower = "";

                    Log.d("Cool Checked", "onClick: " + amber);
                    Log.d("Cool Checked", "onClick: " + oud);
                    Log.d("Cool Checked", "onClick: " + wood);
                    Log.d("Cool Checked", "onClick: " + fruit);
                    Log.d("Cool Checked", "onClick: " + powder);
                    Log.d("Cool Checked", "onClick: " + spices);
                    Log.d("Cool Checked", "onClick: " + leather);
                    Log.d("Cool Checked", "onClick: " + musk);
                    Log.d("Cool Checked", "onClick: " + sweet);
                    Log.d("Cool Checked", "onClick: " + fragrant_plant);
                    Log.d("Cool Checked", "onClick: " + flower);
                }

            }
        });

        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_check_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout.getVisibility() == View.GONE)
                    linearLayout.setVisibility(View.VISIBLE);
                else if (linearLayout.getVisibility() == View.VISIBLE)
                    linearLayout.setVisibility(View.GONE);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (
                        !check_cool.isChecked() && !check_warm.isChecked() && !check_morning.isChecked()
                        && !check_daily.isChecked() && !check_night.isChecked()
                        && !check_amber.isChecked() && !check_oud.isChecked() && !check_wood.isChecked()
                        && !check_fruit.isChecked() && !check_powder.isChecked() && !check_spices.isChecked()
                        && !check_leather.isChecked() && !check_musk.isChecked() && !check_sweet.isChecked()
                        && !check_fragrant_plant.isChecked() && !check_flower.isChecked()) {

                    Toast.makeText(getActivity(), "Atleast One Click One CheckBox For Better Filter Search", Toast.LENGTH_SHORT).show();

                }

                else {
                    linearLayout.setVisibility(View.GONE);

                    PerfumeFilterRequest filterRequest = new PerfumeFilterRequest();
                    filterRequest.setCool(cool);
                    filterRequest.setWarm(warm);
                    filterRequest.setMorning(morning);
                    filterRequest.setDaily(daily);
                    filterRequest.setNight(night);
                    filterRequest.setAmber(amber);
                    filterRequest.setOud(oud);
                    filterRequest.setWood(wood);
                    filterRequest.setFruit(fruit);
                    filterRequest.setPowder(powder);
                    filterRequest.setSpices(spices);
                    filterRequest.setLeather(leather);
                    filterRequest.setMusk(musk);
                    filterRequest.setSweet(sweet);
                    filterRequest.setFragrant_plant(fragrant_plant);
                    filterRequest.setFlower(flower);

                    Log.d("FilterRequest", "onClick: " + cool);
                    Log.d("FilterRequest", "onClick: " + warm);
                    Log.d("FilterRequest", "onClick: " + morning);
                    Log.d("FilterRequest", "onClick: " + daily);
                    Log.d("FilterRequest", "onClick: " + night);
                    Log.d("FilterRequest", "onClick: " + amber);
                    Log.d("FilterRequest", "onClick: " + oud);
                    Log.d("FilterRequest", "onClick: " + wood);
                    Log.d("FilterRequest", "onClick: " + fruit);
                    Log.d("FilterRequest", "onClick: " + powder);
                    Log.d("FilterRequest", "onClick: " + spices);
                    Log.d("FilterRequest", "onClick: " + leather);
                    Log.d("FilterRequest", "onClick: " + musk);
                    Log.d("FilterRequest", "onClick: " + sweet);
                    Log.d("FilterRequest", "onClick: " + fragrant_plant);
                    Log.d("FilterRequest", "onClick: " + flower);
                    new PerfumeFilter().execute(filterRequest);
                }
            }
        });

        new Perfume().execute();

        btnPriceLowtoHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PerfumeByPrice().execute();
            }
        });

        return view;
    }

    public class Perfume extends AsyncTask<PerfumeRequest, Integer, PerfumeResponse> {

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            showDialog();
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);

            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        @Override
        protected PerfumeResponse doInBackground(PerfumeRequest... params) {

            PerfumeResponse response = null;
            try {

                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getPerfumes();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(PerfumeResponse perfumeResponse) {

            try {
                hideDialoge();
                adapter = new PerfumeFragmentAdapter(getContext(), perfumeResponse.getProduct(), getActivity());
                recyclerViewPerfume.setVisibility(View.VISIBLE);
                recyclerOrderByPrice.setVisibility(View.GONE);
                recyclerFilter.setVisibility(View.GONE);
                recyclerViewPerfume.setAdapter(adapter);
                recyclerViewPerfume.setHasFixedSize(true);
                recyclerViewPerfume.setNestedScrollingEnabled(false);
                recyclerViewPerfume.setLayoutManager(new GridLayoutManager(getActivity(), 2));

            } catch (Exception e) {
                System.out.print("" + e);
            }


        }
    }

    public class PerfumeByPrice extends AsyncTask<Void, Void, PerfumeResponse> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            showDialog();
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        @Override
        protected PerfumeResponse doInBackground(Void... params) {
            PerfumeResponse perfumeResponse = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                perfumeResponse = retrofitRest.getPerfumeByPrice();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return perfumeResponse;
        }

        @Override
        protected void onPostExecute(PerfumeResponse perfumeResponse) {

            try {
                hideDialoge();
                adapter = new PerfumeFragmentAdapter(getContext(), perfumeResponse.getProduct(), getActivity());
                recyclerViewPerfume.setVisibility(View.GONE);
                recyclerOrderByPrice.setVisibility(View.VISIBLE);
                recyclerFilter.setVisibility(View.GONE);
                recyclerOrderByPrice.setAdapter(adapter);
                recyclerOrderByPrice.setHasFixedSize(true);
                recyclerOrderByPrice.setNestedScrollingEnabled(false);
                recyclerOrderByPrice.setLayoutManager(new GridLayoutManager(getActivity(), 2));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public class PerfumeFilter extends AsyncTask<PerfumeFilterRequest, Void, PerfumeResponse> {

        RestAdapter restAdapter = null;

        @Override
        protected void onPreExecute() {
            showDialog();

            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);

            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        @Override
        protected PerfumeResponse doInBackground(PerfumeFilterRequest... params) {
            PerfumeResponse response = null;
            try {

                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getFilterPerfume(params[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(PerfumeResponse perfumeResponse) {
            try {
                hideDialoge();

                adapter = new PerfumeFragmentAdapter(getContext(), perfumeResponse.getProduct(), getActivity());
                recyclerViewPerfume.setVisibility(View.GONE);
                recyclerOrderByPrice.setVisibility(View.GONE);
                recyclerFilter.setVisibility(View.VISIBLE);
                recyclerFilter.setAdapter(adapter);
                recyclerFilter.setHasFixedSize(true);
                recyclerFilter.setNestedScrollingEnabled(false);
                recyclerFilter.setLayoutManager(new GridLayoutManager(getActivity(), 2));

            } catch (Exception e) {
                System.out.print("" + e);
            }
        }
    }

    public void showDialog(){
        progressDialog.setMessage("please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
    public void hideDialoge(){
        progressDialog.dismiss();
    }
}
