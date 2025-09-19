// Scripts/TreasureMiningController.cs
using UnityEngine;

public class TreasureMiningController : MonoBehaviour {
    public JwtAuthClient auth;
    public GameApiClient api;

    private async void Start() {
   
        bool ok = await auth.Login("demo", "demo123");
        if (ok) {
            await api.Mine("GOLD", 3);
        }
    }
}