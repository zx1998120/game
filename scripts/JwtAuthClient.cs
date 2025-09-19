// Scripts/JwtAuthClient.cs
using System.Text;
using UnityEngine;
using UnityEngine.Networking;
using System.Threading.Tasks;

[System.Serializable] public class LoginReq { public string username; public string password; }
[System.Serializable] public class LoginResp { public string token; }

public class JwtAuthClient : MonoBehaviour {
    public string baseUrl = "http://localhost:8080/api";
    public string jwtToken;

    public async Task<bool> Login(string username, string password) {
        var req = new LoginReq { username = username, password = password };
        var json = JsonUtility.ToJson(req);
        using var uwr = new UnityWebRequest($"{baseUrl}/auth/login", "POST");
        byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
        uwr.uploadHandler = new UploadHandlerRaw(bodyRaw);
        uwr.downloadHandler = new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");
        var op = uwr.SendWebRequest();
        while (!op.isDone) await Task.Yield();

        if (uwr.result != UnityWebRequest.Result.Success) {
            Debug.LogError($"Login failed: {uwr.error} {uwr.downloadHandler.text}");
            return false;
        }
        var resp = JsonUtility.FromJson<LoginResp>(uwr.downloadHandler.text);
        jwtToken = resp.token;
        return true;
    }
}