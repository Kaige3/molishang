package kaigee.top.role.controller;
import kaigee.top.infrastructure.model.QueryRequest;
import kaigee.top.role.entity.Role;
import kaigee.top.role.entity.dto.RoleInput;
import kaigee.top.role.entity.dto.RoleSpec;
import kaigee.top.role.repository.RoleRepository;
import kaigee.top.role.service.RoleService;
import lombok.AllArgsConstructor;
import org.babyfish.jimmer.client.FetchBy;
import org.babyfish.jimmer.client.meta.DefaultFetcherOwner;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@AllArgsConstructor
@DefaultFetcherOwner(RoleRepository.class)
public class RoleController {
    private final RoleService roleService;

    @GetMapping("{id}")
    public @FetchBy(value = "ROLE_MENU_FETCHER") Role findById(@PathVariable String id) {
        return roleService.findById(id);
    }

    @PostMapping("query")
    public Page< @FetchBy(value = "COMPLEX_FETCHER") Role> query(@RequestBody QueryRequest<RoleSpec> queryRequest) {
        return roleService.query(queryRequest);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated RoleInput role) {
        return roleService.save(role);
    }

    @PostMapping("delete")
    public Boolean delete(@RequestBody List<String> ids) {
        return roleService.delete(ids);
    }
}