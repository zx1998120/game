// Scripts/GameApiClient.cs
using System.Text;
using UnityEngine;
using UnityEngine.Networking;
using System.Threading.Tasks;

[System.Serializable] public class MineReq { public string itemCode; public int count; }

public class GameApiClient : MonoBehaviour {
    public string baseUrl = "http://localhost:8080/api";
    public JwtAuthClient auth;

    public async Task<bool> Mine(string itemCode, int count = 1) {
        if (string.IsNullOrEmpty(auth.jwtToken)) { Debug.LogError("Not logged in"); return false; }
        var req = new MineReq { itemCode = itemCode, count = count };
        var json = JsonUtility.ToJson(req);

        using var uwr = new UnityWebRequest($"{baseUrl}/game/mine", "POST");
        uwr.uploadHandler = new UploadHandlerRaw(Encoding.UTF8.GetBytes(json));
        uwr.downloadHandler = new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");
        uwr.SetRequestHeader("Authorization", $"Bearer {auth.jwtToken}");
        var op = uwr.SendWebRequest();
        while (!op.isDone) await Task.Yield();

        if (uwr.result != UnityWebRequest.Result.Success) {
            Debug.LogError($"Mine failed: {uwr.error} {uwr.downloadHandler.text}");
            return false;
        }
        Debug.Log($"Mine queued: {uwr.downloadHandler.text}");
        return true;
    }
}