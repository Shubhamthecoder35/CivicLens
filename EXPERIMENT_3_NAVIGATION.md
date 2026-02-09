# Experiment 3: Multiple Screens with Intents and Fragments

**Objective**: Demonstrate navigation between multiple screens using Intents and implement Fragments for modular UI components.

---

## Implementation Summary

### ✅ Completed Components

#### 1. **Intent-based Navigation**

**What are Intents?**
Intents are messaging objects used to request an action from another app component, primarily used for starting activities.

**Implementation:**
- **MainActivity → MapActivity**: Click on map area in home screen
- **ReportDetailsActivity → MapActivity**: Click on map card in report details
- **Bottom Navigation**: All screens navigate using Intents (via BottomNavHelper)

**Example Code:**
```java
// MainActivity.java
binding.mapArea.setOnClickListener(v -> {
    Intent intent = new Intent(this, MapActivity.class);
    startActivity(intent);
});
```

**Activities communicating via Intents:**
- `MainActivity` ↔ `CreateReportActivity`
- `MainActivity` ↔ `MyReportsActivity`
- `MainActivity` ↔ `ProfileActivity`
- `MyReportsActivity` → `ReportDetailsActivity`
- `ReportDetailsActivity` → `MapActivity`
- `MainActivity` → `MapActivity`

---

#### 2. **Fragments Implementation**

**What are Fragments?**
Fragments represent reusable portions of your app's UI. A fragment defines its own layout and lifecycle, can be combined in activities for flexible UI designs.

**Implementation:**

##### **MapFragment** ([fragment_map.xml](app/src/main/res/layout/fragment_map.xml))
- **Purpose**: Display interactive map interface
- **Components**:
  - Map image display
  - Location marker overlay
  - Zoom controls (zoom in/out buttons)
  - Location information card
  - Action buttons (Recenter, Select Location)

**Key Features:**
```xml
<!-- fragment_map.xml -->
<!-- Map Image -->
<ImageView android:id="@+id/ivMapImage" />

<!-- Location Marker -->
<ImageView android:id="@+id/ivLocationMarker" />

<!-- Zoom Controls Card -->
<MaterialCardView android:id="@+id/cardZoom">
    <ImageButton android:id="@+id/btnZoomIn" />
    <ImageButton android:id="@+id/btnZoomOut" />
</MaterialCardView>

<!-- Controls Card -->
<MaterialCardView android:id="@+id/cardControls">
    <TextView android:id="@+id/tvLocationAddress" />
    <TextView android:id="@+id/tvCoordinates" />
    <Button android:id="@+id/btnRecenter" />
    <Button android:id="@+id/btnSelectLocation" />
</MaterialCardView>
```

##### **MapFragment.java** ([MapFragment.java](app/src/main/java/com/example/civiclens/MapFragment.java))
**Demonstrates:**
- Fragment lifecycle (`onCreateView`, `onViewCreated`, `onDestroyView`)
- View binding in fragments
- Fragment-to-Activity communication via interface pattern

**Key Code:**
```java
public class MapFragment extends Fragment {
    private FragmentMapBinding binding;
    private MapFragmentListener listener;

    // Interface for communicating with host Activity
    public interface MapFragmentListener {
        void onLocationSelected(double latitude, double longitude);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupClickListeners();
        updateLocationDisplay();
    }

    public void setListener(MapFragmentListener listener) {
        this.listener = listener;
    }
}
```

---

#### 3. **MapActivity - Fragment Host** ([MapActivity.java](app/src/main/java/com/example/civiclens/MapActivity.java))

**Purpose**: Container activity that hosts MapFragment

**Demonstrates:**
- Fragment transactions
- Fragment lifecycle management
- Fragment-to-Activity communication
- Configuration change handling

**Key Implementation:**
```java
public class MapActivity extends AppCompatActivity 
    implements MapFragment.MapFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        
        // Load fragment only if not already loaded
        if (savedInstanceState == null) {
            loadMapFragment();
        }
    }

    private void loadMapFragment() {
        MapFragment mapFragment = MapFragment.newInstance();
        mapFragment.setListener(this);

        // Fragment Transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, mapFragment, "MAP_FRAGMENT");
        transaction.commit();
    }

    @Override
    public void onLocationSelected(double latitude, double longitude) {
        // Handle location selection from fragment
        String message = String.format("Location received from Fragment:\\nLat: %.4f, Lng: %.4f", 
            latitude, longitude);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
```

**activity_map.xml** Layout:
```xml
<androidx.constraintlayout.widget.ConstraintLayout>
    <!-- Toolbar -->
    <MaterialToolbar android:id="@+id/toolbar" />

    <!-- Fragment Container -->
    <FragmentContainerView
        android:id="@+id/fragmentContainer"
        android:layout_width="0dp"
        android:layout_height="0dp"
        tools:layout="@layout/fragment_map" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## Key Concepts Demonstrated

### 1. **Intent Navigation**
- **Explicit Intents**: Direct navigation to specific activities
- **Activity Lifecycle**: Managing activity stack
- **Back Navigation**: Proper back button handling

### 2. **Fragment Lifecycle**
```
onAttach()
    ↓
onCreate()
    ↓
onCreateView()      ← Inflate layout
    ↓
onViewCreated()     ← Setup UI
    ↓
onStart()
    ↓
onResume()
    ↓
[Fragment is Active]
    ↓
onPause()
    ↓
onStop()
    ↓
onDestroyView()     ← Clean up binding
    ↓
onDestroy()
    ↓
onDetach()
```

### 3. **Fragment Communication Patterns**

#### **Fragment → Activity Communication**
Using interface pattern:
```java
// In Fragment
public interface MapFragmentListener {
    void onLocationSelected(double latitude, double longitude);
}

// In Activity
public class MapActivity extends AppCompatActivity 
    implements MapFragment.MapFragmentListener {
    
    @Override
    public void onLocationSelected(double latitude, double longitude) {
        // Handle callback
    }
}
```

### 4. **Fragment Transactions**
```java
FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
transaction.replace(R.id.fragmentContainer, fragment, "TAG");
transaction.commit();
```

---

## Testing Instructions

### Test Intent Navigation:
1. **Launch app** → Opens `MainActivity`
2. **Click map area on home screen** → Opens `MapActivity` (Intent)
3. **Press back** → Returns to `MainActivity`
4. **Navigate to any report** → Click map card → Opens `MapActivity` (Intent)

### Test Fragment Behavior:
1. **Open MapActivity**
2. **Click Zoom In** → Shows toast (Fragment handling)
3. **Click Zoom Out** → Shows toast (Fragment handling)
4. **Click Recenter** → Updates location display (Fragment logic)
5. **Click Select Location** → Fragment communicates with Activity (Interface callback)
6. **Rotate device** → Fragment survives configuration change

### Test Fragment Communication:
1. **Open MapActivity**
2. **Click "Select Location" button**
3. **Observe toast** → Shows coordinates received from Fragment to Activity

---

## Code Structure

```
app/src/main/
├── java/com/example/civiclens/
│   ├── MapActivity.java              # Fragment host activity
│   ├── MapFragment.java              # Map fragment implementation
│   ├── MainActivity.java             # Updated with Intent to MapActivity
│   ├── ReportDetailsActivity.java   # Updated with Intent to MapActivity
│   └── ... (other activities)
│
└── res/layout/
    ├── activity_map.xml              # MapActivity layout with FragmentContainerView
    ├── fragment_map.xml              # MapFragment UI layout
    └── ... (other layouts)
```

---

## Integration Points (Future Experiments)

### **Experiment 7: GPS Integration**
- Replace simulated coordinates with real GPS data
- Update `MapFragment` to show actual user location
- Implement location permission handling

### **Experiment 8: Media Handling**
- Capture photos and display markers on map
- Show report images on map locations

### **Experiment 10: Database**
- Save selected locations to Room Database
- Display saved reports as markers on map

---

## Best Practices Implemented

✅ **Fragment Factory Pattern**: Using `newInstance()` static method  
✅ **View Binding**: Safe view references in both Activity and Fragment  
✅ **Lifecycle Awareness**: Proper cleanup in `onDestroyView()` and `onDestroy()`  
✅ **Interface Communication**: Decoupled Fragment-Activity communication  
✅ **Configuration Changes**: Fragment survives rotation via FragmentManager  
✅ **Single Responsibility**: Fragment handles UI, Activity handles navigation  
✅ **Explicit Intents**: Type-safe activity navigation  

---

## Summary

This implementation successfully demonstrates **Experiment 3** requirements:

✅ **Multiple Screens**: MainActivity, MapActivity, ReportDetailsActivity, CreateReportActivity, MyReportsActivity, ProfileActivity, NotificationsActivity  
✅ **Intents**: Navigation between all activities using explicit Intents  
✅ **Fragments**: MapFragment with lifecycle, communication, and transactions  
✅ **Fragment Hosting**: MapActivity manages fragment lifecycle  
✅ **Communication**: Interface-based Fragment-to-Activity callbacks  

**Next Experiments:**
- **Experiment 4**: Gesture handling (swipe, long press)
- **Experiment 5**: SharedPreferences for user data
- **Experiment 7**: GPS sensor integration for real location data
