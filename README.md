# 📃VelocityWhitelist
A Proxy wide Name-based Whitelist for Velocity (Fork of https://github.com/james090500/VelocityWhitelist)

## Permissions
| Permission | Purpose |
|--|--|
| `vwhitelist.admin` | Needed for all `/vwhitelist` commands |
| `vwhitelist.bypass` | Can bypass whitelist even if not in list |

## Commands
| Command | Response |
|--|--|
| `/vwhitelist` | Info command |
| `/vwhitelist on` | Turn the whitelist on |
| `/vwhitelist off` | Turn the whitelist off |
| `/vwhitelist add <username>` | Add a user to the whitelist |
| `/vwhitelist remove <username>` | Remove a user from the whitelist |
| `/vwhitelist reload` | Reload the whitelist config |

## Config
```toml
# Whether the whitelist should be on or off
enabled = true
# The message to be shown on user disconnect
message = "&cWhitelist enabled!"
```

## Download
[Download from releases page.](https://github.com/erxson/VelocityWhitelist/releases)


<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.
